import java.util.LinkedList

var r = 0
var c = 0
var sr = 0
var sc = 0
lateinit var board: Array<CharArray>
lateinit var visit: Array<BooleanArray>
lateinit var dist: Array<IntArray>

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        r = it[0]
        c = it[1]
    }
    board = Array(r) { readLine().toCharArray() }
    visit = Array(r) { BooleanArray(c) }
    dist = Array(r) { IntArray(c) { Int.MAX_VALUE } }

    run loop@ {
        for (i in 0 until r) {
            for (j in 0 until c) {
                if (board[i][j] == 'J') {
                    sr = i
                    sc = j

                    return@loop
                }
            }
        }
    }
}

fun solve() {
    spreadFire()

    val que = LinkedList<Int>()
    que.add(sr)
    que.add(sc)
    que.add(0)
    visit[sr][sc] = true

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        val time = que.poll()

        if (!isSafe(row, col, time)) continue
        if (isEscapable(row, col)) {
            println(time + 1)
            return
        }

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (visit[nr][nc] || board[nr][nc] != '.') return@forEach

            que.add(nr)
            que.add(nc)
            que.add(time + 1)
            visit[nr][nc] = true
        }
    }

    println("IMPOSSIBLE")
}

fun spreadFire() {
    val que = LinkedList<Int>()
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (board[i][j] == 'F') {
                que.add(i)
                que.add(j)
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

            if (nr !in 0 until r || nc !in 0 until c) return@forEach
            if (dist[nr][nc] != Int.MAX_VALUE || board[nr][nc] == '#') return@forEach

            que.add(nr)
            que.add(nc)
            dist[nr][nc] = dist[row][col] + 1
        }
    }
}

fun isEscapable(row: Int, col: Int) = row == 0 || row == r - 1 || col == 0 || col == c - 1

fun isSafe(row: Int, col: Int, time: Int) = time < dist[row][col]

fun main() {
    input()
    solve()
}