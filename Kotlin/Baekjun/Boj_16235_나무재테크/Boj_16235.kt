import java.util.*
import kotlin.collections.HashSet

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1),
    arrayOf(1, 1),
    arrayOf(-1, 1),
    arrayOf(1, -1),
    arrayOf(-1, -1)
)
var n = 0
var m = 0
var k = 0
lateinit var energy: Array<IntArray>
lateinit var amount: List<List<Int>>
lateinit var tree: List<List<LinkedList<Int>>>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        k = nextToken().toInt()
    }
    energy = Array(n) { IntArray(n) { 5 } }
    amount = List(n) { readLine().split(" ").map { it.toInt() } }
    tree = List(n) { List(n) { LinkedList() } }

    repeat(m) {
        val (row, col, age) = readLine().split(" ").map { it.toInt() }
        tree[row - 1][col - 1].add(age)
    }
}

fun solve() {
    repeat(k) {
        grow()
        breed()
        addEnergy()
    }
    var ans = 0
    for (row in 0 until n) {
        for (col in 0 until n) {
            ans += tree[row][col].size
        }
    }
    println(ans)
}

fun grow() {
    for (row in 0 until n) {
        for (col in 0 until n) {
            val temp = arrayListOf<Int>()
            tree[row][col].sort()
            while (tree[row][col].isNotEmpty()) {
                val target = tree[row][col].poll()
                if (energy[row][col] < target) {
                    energy[row][col] += (target / 2 + tree[row][col].sumOf { it / 2 })
                    tree[row][col].clear()
                    break
                }
                energy[row][col] -= target
                temp.add(target + 1)
            }
            tree[row][col].addAll(temp)
        }
    }
}

fun breed() {
    for (row in 0 until n) {
        for (col in 0 until n) {
            val cnt = tree[row][col].count { it % 5 == 0 }
            dir.forEach {
                val nr = row + it[0]
                val nc = col + it[1]

                if (nr !in 0 until n || nc !in 0 until n) return@forEach

                repeat(cnt) {
                    tree[nr][nc].add(1)
                }
            }
        }
    }
}

fun addEnergy() {
    for (row in 0 until n) {
        for (col in 0 until n) {
            energy[row][col] += amount[row][col]
        }
    }
}

fun main() {
    input()
    solve()
}