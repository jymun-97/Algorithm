import java.util.*
import kotlin.collections.LinkedHashMap

var n = 0
var m = 0
var k = 0
lateinit var energy: Array<IntArray>
lateinit var plusEnergy: Array<IntArray>
lateinit var board: Array<Array<LinkedHashMap<Int, Int>>>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        k = nextToken().toInt()
    }
    energy = Array(n) { IntArray(n) { 5 } }
    plusEnergy = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    board = Array(n) { Array(n) { LinkedHashMap() } }

    repeat(m) {
        val (row, col, age) = readLine().split(" ").map { it.toInt() }
        addTree(row - 1, col - 1, age)
    }
}

fun solve() {
//    printBoard()
    repeat(k) {
        grow()
        breed()
        supply()
//        printBoard()
    }
    println(
        board.sumOf { row ->
            row.sumOf {
                it.values.sum()
            }
        }
    )
}

fun printBoard() {
    for (i in 0 until n) {
        for (j in 0 until n) {
            println("${i + 1}, ${j + 1} => ${board[i][j].keys} : ${board[i][j].values}")
        }
    }
    println()
}

fun grow() {
    for (i in 0 until n) {
        for (j in 0 until n) {
            val info = LinkedList<Pair<Int, Int>>()
            val dead = LinkedList<Pair<Int, Int>>()

            board[i][j].forEach { (age, cnt) ->
                val need = cnt * age
                if (need <= energy[i][j]) {
                    energy[i][j] -= need
                    info.add(age + 1 to cnt)
                } else {
                    val nTargets = energy[i][j] / age
                    energy[i][j] -= nTargets * age
                    dead.add(age to cnt - nTargets)

                    if (nTargets > 0) info.add(age + 1 to nTargets)
                }
            }
            board[i][j].clear()
            while (info.isNotEmpty()) {
                val (age, cnt) = info.poll()
                board[i][j][age] = cnt
            }
            while (dead.isNotEmpty()) {
                val (age, cnt) = dead.poll()
                energy[i][j] += (age / 2) * cnt
            }
        }
    }
}

fun breed() {
    val dir = arrayOf(
        arrayOf(1, 0), arrayOf(1, 1),
        arrayOf(-1, 0), arrayOf(-1, 1),
        arrayOf(0, 1), arrayOf(1, -1),
        arrayOf(0, -1), arrayOf(-1, -1)
    )
    for (row in 0 until n) {
        for (col in 0 until n) {
            val cnt = board[row][col].filter { it.key % 5 == 0 }.values.sum()
            if (cnt == 0) continue

            dir.forEach {
                val nr = row + it[0]
                val nc = col + it[1]

                if (nr !in 0 until n || nc !in 0 until n) return@forEach

                addTree(nr, nc, 1, cnt)
            }
        }
    }
}

fun supply() {
    for (i in 0 until n) {
        for (j in 0 until n) {
            energy[i][j] += plusEnergy[i][j]
        }
    }
}

fun addTree(row: Int, col: Int, age: Int, count: Int = 1) {
    val temp = LinkedHashMap(board[row][col])
    board[row][col].apply {
        clear()
        put(age, count + (temp[age] ?: 0))
        putAll(temp.filter { it.key != age })
    }
}

fun main() {
    input()
    solve()
}