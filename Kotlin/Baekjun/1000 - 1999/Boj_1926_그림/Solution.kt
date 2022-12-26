import java.util.LinkedList
import kotlin.math.max

var n = 0
var m = 0
lateinit var board: Array<IntArray>
lateinit var visit: Array<BooleanArray>

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        m = this[1]
    }
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    visit = Array(n) { BooleanArray(m) }
}

fun solve() {
    var count = 0
    var size = 0
    for (row in 0 until n) {
        for (col in 0 until m) {
            if (visit[row][col] || board[row][col] == 0) continue

            count++
            size = max(size, bfs(row, col))
        }
    }
    println(
        buildString {
            appendLine(count)
            appendLine(size)
        }
    )
}

fun bfs(sr: Int, sc: Int): Int {
    var size = 0
    val que = LinkedList<Int>().apply {
        add(sr)
        add(sc)
        visit[sr][sc] = true
    }
    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        size++

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until m) return@forEach
            if (visit[nr][nc] || board[nr][nc] == 0) return@forEach

            que.apply {
                add(nr)
                add(nc)
                visit[nr][nc] = true
            }
        }
    }
    return size
}

fun main() {
    input()
    solve()
}