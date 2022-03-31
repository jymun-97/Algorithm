import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

val dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(0, -1),
)

var n = 0
var m = 0
lateinit var map : Array<IntArray>
lateinit var dp : Array<IntArray>

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    map = Array(n + 1) { IntArray(m + 1) }
    dp = Array(n + 1) { IntArray(m + 1) { -1 } }

    for (i in 1..n) {
        with(StringTokenizer(readLine())) {
            for (j in 1..m) map[i][j] = nextToken().toInt()
        }
    }
}

fun solve() {
    val answer = dfs(1, 1)
    println(answer)
    for (i in 1..n) {
        for (j in 1..m) {
            print("${dp[i][j]} ")
        }
        println()
    }
}

fun dfs(row: Int, col: Int) : Int {
    if (row == n && col == m) return 1
    if (dp[row][col] != -1) return dp[row][col]

    dp[row][col] = 0
    for (i in 0 until 4) {
        val nr = row + dir[i][0]
        val nc = col + dir[i][1]

        if (nr < 1 || nr > n || nc < 1 || nc > m) continue
        if (map[row][col] <= map[nr][nc]) continue

        dp[row][col] += dfs(nr, nc)
    }

    return dp[row][col]
}

fun main() {
    input()
    solve()
}