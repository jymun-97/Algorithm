import java.util.*
import kotlin.math.max

data class Point(
    val row: Int,
    val col: Int
)
var n = 0
var m = 0
var ans = 0
lateinit var map: Array<IntArray>
lateinit var visit: Array<BooleanArray>

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    map = Array(n) { readLine().split(' ').map { it.toInt() }.toIntArray() }
    visit = Array(n) { BooleanArray(m) }
}

fun solve() {
    for (row in 0 until n) {
        for (col in 0 until m) {
            dfs(0, row, col, 0)

            var sum = map[row][col]
            for (i in 0 until 4) {
                val nr = row + dir[i][0]
                val nc = col + dir[i][1]

                if (nr !in 0 until n || nc !in 0 until m) continue

                sum += map[nr][nc]
            }
            for (i in 0 until 4) {
                val nr = row + dir[i][0]
                val nc = col + dir[i][1]

                if (nr !in 0 until n || nc !in 0 until m) {
                    ans = max(ans, sum)
                    continue
                }

                sum -= map[nr][nc]
                ans = max(ans, sum)
                sum += map[nr][nc]
            }
        }
    }

    println(ans)
}

fun dfs(k: Int, row: Int, col: Int, sum: Int) {
    if (k == 4) {
        ans = max(ans, sum)
        return
    }

    dir.forEach {
        val nr = row + it[0]
        val nc = col + it[1]

        if (nr !in 0 until n || nc !in 0 until m) return@forEach
        if (visit[nr][nc]) return@forEach

        visit[nr][nc] = true
        dfs(k + 1, nr, nc, sum + map[nr][nc])
        visit[nr][nc] = false
    }
}

fun main() {
    input()
    solve()
}