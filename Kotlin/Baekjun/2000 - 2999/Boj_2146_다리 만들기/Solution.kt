import java.util.LinkedList

var n = 0
lateinit var board: Array<IntArray>
lateinit var dist: Array<IntArray>

val dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    dist = Array(n) { IntArray(n) { Int.MAX_VALUE } }
}

fun solve() {
    var group = 2
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (board[i][j] == 1) {
                makeGroup(i, j, group++)
            }
        }
    }

    println(
        bfs()
    )
}

fun makeGroup(sr: Int, sc: Int, group: Int) {
    val que = LinkedList<Int>().apply {
        add(sr)
        add(sc)

        board[sr][sc] = group
    }

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until n) return@forEach
            if (board[nr][nc] != 1) return@forEach

            que.apply {
                add(nr)
                add(nc)

                board[nr][nc] = group
            }
        }
    }
}

fun bfs(): Int {
    var min = Int.MAX_VALUE
    val que = LinkedList<Int>()
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (board[i][j] != 0) que.apply {
                add(i)
                add(j)

                dist[i][j] = 0
            }
        }
    }

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            when {
                nr !in 0 until n || nc !in 0 until n -> return@forEach

                board[nr][nc] == board[row][col] -> return@forEach

                board[nr][nc] == 0 -> que.apply {
                    add(nr)
                    add(nc)

                    board[nr][nc] = board[row][col]
                    dist[nr][nc] = dist[row][col] + 1
                }

                else -> min = minOf(min, dist[nr][nc] + dist[row][col])
            }
        }
    }

    return min
}

fun main() {
    input()
    solve()
}