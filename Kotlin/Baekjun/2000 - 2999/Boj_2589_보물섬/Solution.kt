import java.util.LinkedList

var n = 0
var m = 0
lateinit var board: Array<String>

val dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    board = Array(n) { readLine() }
}

fun solve() {
    var ans = 0
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (board[i][j] == 'L') {
                ans = maxOf(ans, bfs(i, j))
            }
        }
    }

    println(ans)
}

fun bfs(sr: Int, sc: Int): Int {
    var max = 0
    val visit = Array(n) { BooleanArray(m) }
    val que = LinkedList<Int>().apply {
        add(sr)
        add(sc)
        add(0)

        visit[sr][sc] = true
    }

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        val dist = que.poll()

        max = maxOf(max, dist)

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until m) return@forEach
            if (visit[nr][nc] || board[nr][nc] == 'W') return@forEach

            que.apply {
                add(nr)
                add(nc)
                add(dist + 1)

                visit[nr][nc] = true
            }
        }
    }

    return max
}

fun main() {
    input()
    solve()
}