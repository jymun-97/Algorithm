import java.util.LinkedList

var n = 0
var m = 0
val targets = LinkedList<Int>()
lateinit var board: Array<IntArray>
lateinit var visit: Array<BooleanArray>

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
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }

    for (i in 0 until n) {
        for (j in 0 until m) {
            if (board[i][j] == 0) continue

            targets.add(i)
            targets.add(j)
        }
    }

    targets.add(-1)
}

fun solve() {
    bfs()

    var ans = 0
    while (true) {
        if (targets.peek() == -1) {
            targets.poll()
            ans++

            if (targets.isEmpty()) break
            else {
                bfs()
                targets.add(-1)
                continue
            }
        }

        val row = targets.poll()
        val col = targets.poll()
        val count = dir.count {
            val nr = row + it[0]
            val nc = col + it[1]

            visit[nr][nc]
        }

        if (count < 2) targets.apply {
            add(row)
            add(col)
        } else {
            board[row][col] = 0
        }
    }

    println(ans)
}

fun bfs() {
    visit = Array(n) { BooleanArray(m) }
    val que = LinkedList<Int>().apply {
        add(0)
        add(0)

        visit[0][0] = true
    }

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until m) return@forEach
            if (visit[nr][nc] || board[nr][nc] == 1) return@forEach

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