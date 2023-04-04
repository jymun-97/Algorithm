import java.util.PriorityQueue

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

data class Info(
    val row: Int,
    val col: Int,
    val dist: Int
)
var n = 0
var count = 1
lateinit var board: Array<IntArray>
lateinit var dist: Array<IntArray>

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(reader) {
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    dist = Array(n) { IntArray(n) { Int.MAX_VALUE } }
}

fun solve() {
    builder.append("Problem ")
        .append(count++)
        .append(": ")
        .appendLine(dijkstra())
}

fun dijkstra() : Int {
    val que = PriorityQueue<Info>() { i1, i2 -> i1.dist - i2.dist }
    que.add(Info(0, 0, board[0][0]))
    dist[0][0] = board[0][0]

    while (que.isNotEmpty()) {
        val from = que.poll()

        if (from.dist > dist[from.row][from.col]) continue

        dir.forEach {
            val nr = from.row + it[0]
            val nc = from.col + it[1]

            if (nr !in 0 until n || nc !in 0 until n) return@forEach

            val next = from.dist + board[nr][nc]
            if (dist[nr][nc] > next) {
                que.add(Info(nr, nc, next))
                dist[nr][nc] = next
            }
        }
    }

    return dist[n - 1][n - 1]
}

fun main() = with(reader) {
    while (true) {
        n = readLine().toInt()
        if (n == 0) break

        input()
        solve()
    }
    println(builder)
}