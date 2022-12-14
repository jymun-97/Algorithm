import java.util.*

var n = 0
var m = 0
var k = 0
lateinit var board: Array<IntArray>
lateinit var visit: Array<Array<BooleanArray>>

val dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(0, 1),
    intArrayOf(-1, 0),
    intArrayOf(0, -1),
    intArrayOf(2, 1),
    intArrayOf(2, -1),
    intArrayOf(1, 2),
    intArrayOf(1, -2),
    intArrayOf(-1, 2),
    intArrayOf(-1, -2),
    intArrayOf(-2, 1),
    intArrayOf(-2, -1),
)

fun input() = with(System.`in`.bufferedReader()) {
    k = readLine().toInt()
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[1]
        m = this[0]
    }
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    visit = Array(n) { Array(m) { BooleanArray(k + 1) } }
}

fun solve() {
    var ans = -1
    val que = LinkedList<Int>().apply {
        add(0)
        add(0)
        add(0)
        add(k)
        visit[0][0][k] = true
    }
    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        val dist = que.poll()
        val remain = que.poll()

        if (row == n - 1 && col == m - 1) {
            ans = dist
            break
        }

        dir.forEachIndexed { i, it ->
            val nr = row + it[0]
            val nc = col + it[1]

            when {
                nr !in 0 until n || nc !in 0 until m -> return@forEachIndexed

                board[nr][nc] == 1 -> return@forEachIndexed

                i > 3 && remain <= 0 -> return@forEachIndexed

                i > 3 && visit[nr][nc][remain - 1] -> return@forEachIndexed

                i > 3 -> que.apply {
                    add(nr)
                    add(nc)
                    add(dist + 1)
                    add(remain - 1)
                    visit[nr][nc][remain - 1] = true
                }

                visit[nr][nc][remain] -> return@forEachIndexed

                else -> que.apply {
                    que.add(nr)
                    que.add(nc)
                    que.add(dist + 1)
                    que.add(remain)
                    visit[nr][nc][remain] = true
                }
            }
        }
    }
    println(ans)
}

fun main() {
    input()
    solve()
}