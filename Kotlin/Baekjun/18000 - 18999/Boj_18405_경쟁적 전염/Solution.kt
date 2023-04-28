import java.util.LinkedList

var n = 0
var k = 0
var time = 0
var r = 0
var c = 0
lateinit var board: Array<IntArray>
lateinit var visit: Array<BooleanArray>
lateinit var virus: Array<LinkedList<Int>>

val dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        k = it[1]
    }
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    visit = Array(n) { BooleanArray(n) }
    virus = Array(k + 1) { LinkedList() }

    for (i in 0 until n) {
        for (j in 0 until n) {
            if (board[i][j] != 0) virus[board[i][j]].apply {
                add(i)
                add(j)
            }
        }
    }

    readLine().split(" ").map { it.toInt() }.let {
        time = it[0]
        r = it[1] - 1
        c = it[2] - 1
    }
}

fun solve() {
    val que = LinkedList<Int>()
    for (i in 1..k) que.apply {
        while (virus[i].isNotEmpty()) {
            val row = virus[i].poll()
            val col = virus[i].poll()

            add(row)
            add(col)
            add(i)
            add(0)

            visit[row][col] = true
        }
    }

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        val target = que.poll()
        val dist = que.poll()

        if (dist == time) continue

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until n) return@forEach
            if (visit[nr][nc] || board[nr][nc] != 0) return@forEach

            que.apply {
                add(nr)
                add(nc)
                add(target)
                add(dist + 1)

                board[nr][nc] = target
                visit[nr][nc] = true
            }
        }
    }

    println(board[r][c])
}

fun main() {
    input()
    solve()
}