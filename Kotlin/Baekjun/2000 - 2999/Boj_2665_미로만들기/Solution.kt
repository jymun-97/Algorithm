import java.util.PriorityQueue

data class Info(
    val row: Int,
    val col: Int,
    val count: Int
)
var n = 0
lateinit var board: Array<String>
lateinit var count: Array<IntArray>

val dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n) { readLine() }
    count = Array(n) { IntArray(n) { Int.MAX_VALUE } }
}

fun solve() {
    var ans = Int.MAX_VALUE
    val que = PriorityQueue<Info>() { i1, i2 -> i1.count - i2.count }
    que.add(Info(0, 0, 0))
    count[0][0] = 0

    while (que.isNotEmpty()) {
        val from = que.poll()

        if (count[from.row][from.col] < from.count) continue
        if (from.row == n - 1 && from.col == n - 1) {
            ans = minOf(ans, from.count)
            continue
        }

        dir.forEach {
            val nr = from.row + it[0]
            val nc = from.col + it[1]

            if (nr !in 0 until n || nc !in 0 until n) return@forEach

            when {
                board[nr][nc] == '1' && count[nr][nc] == Int.MAX_VALUE -> {
                    que.add(Info(nr, nc, from.count))
                    count[nr][nc] = from.count
                }
                count[nr][nc] > from.count + 1 -> {
                    que.add(Info(nr, nc, from.count + 1))
                    count[nr][nc] = from.count + 1
                }
                else -> return@forEach
            }
        }
    }

    println(ans)
}

fun main() {
    input()
    solve()
}