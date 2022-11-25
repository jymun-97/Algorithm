import java.util.*
import kotlin.math.max

var n = 0
var maxHeight = 0
val que = LinkedList<Int>()
lateinit var board: Array<IntArray>
lateinit var visit: Array<BooleanArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }

    maxHeight = board.maxOf { it.maxOrNull()!! }
}

fun solve() {
    var ans = 1
    repeat (maxHeight) {
        rain()
        visit = Array(n) { BooleanArray(n) }

        var count = 0
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (board[i][j] > 0 && !visit[i][j]) {
                    bfs(i, j)
                    count++
                }
            }
        }
        ans = max(ans, count)
    }
    println(ans)
}

fun rain() {
    board = board.map { it.map { it - 1 }.toIntArray() }.toTypedArray()
}

fun bfs(sr: Int, sc: Int) {
    val dir = arrayOf(
        arrayOf(1, 0),
        arrayOf(-1, 0),
        arrayOf(0, 1),
        arrayOf(0, -1)
    )
    val que = LinkedList<Int>().apply {
        add(sr)
        add(sc)
        visit[sr][sc] = true
    }

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until n) return@forEach
            if (board[nr][nc] <= 0 || visit[nr][nc]) return@forEach

            que.apply {
                add(nr)
                add(nc)
                visit[nr][nc] = true
            }
        }
    }
}

fun main() {
    input()
    solve()
}