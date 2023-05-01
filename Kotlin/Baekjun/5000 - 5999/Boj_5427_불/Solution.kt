import java.util.LinkedList

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

var n = 0
var m = 0

lateinit var board: Array<CharArray>
lateinit var dist: Array<IntArray>

val dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(0, -1)
)

fun input() = with(reader) {
    readLine().split(" ").map { it.toInt() }.let {
        m = it[0]
        n = it[1]
    }
    dist = Array(n) { IntArray(m) { Int.MAX_VALUE } }
    board = Array(n) { readLine().toCharArray() }
}

fun solve() {
    val que = LinkedList<Int>()

    for (i in 0 until n) {
        for (j in 0 until m) when (board[i][j]) {
            '@' -> que.apply {
                addFirst(j)
                addFirst(i)
                addFirst(-1)

                dist[i][j] = 0
            }
            '*' -> que.apply {
                add(-2)
                add(i)
                add(j)
            }
        }
    }

    while (que.isNotEmpty()) {
        val flag = que.poll()
        val row = que.poll()
        val col = que.poll()

        if (flag == -1 && board[row][col] != '*' && (row == 0 || row == n - 1 || col == 0 || col == m - 1)) {
            builder.appendLine(dist[row][col] + 1)
            return
        }

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until m) return@forEach

            if (flag == -1 && dist[nr][nc] == Int.MAX_VALUE && board[nr][nc] == '.') que.apply {
                add(flag)
                add(nr)
                add(nc)

                dist[nr][nc] = dist[row][col] + 1
            }
            if (flag == -2 && board[nr][nc] != '*' && board[nr][nc] != '#') que.apply {
                add(flag)
                add(nr)
                add(nc)

                board[nr][nc] = '*'
            }
        }
    }

    builder.appendLine("IMPOSSIBLE")
}

fun main() = with(reader) {
    repeat(readLine().toInt()) {
        input()
        solve()
    }
    println(builder)
}