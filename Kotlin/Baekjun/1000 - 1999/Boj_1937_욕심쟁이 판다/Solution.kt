
var n = 0
lateinit var board: Array<IntArray>
lateinit var dp: Array<IntArray>

val dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    dp = Array(n) { IntArray(n) }
}

fun solve() {
    var ans = 0
    for (i in 0 until n) {
        for (j in 0 until n) {
            ans = maxOf(ans, dfs(i, j))
        }
    }
    println(ans)
}

fun dfs(row: Int, col: Int): Int {
    if (dp[row][col] != 0) return dp[row][col]

    dp[row][col] = dir.map { row + it[0] to col + it[1] }
        .filter { it.first in 0 until n && it.second in 0 until n && board[it.first][it.second] > board[row][col] }
        .ifEmpty { return 1 }
        .maxOf { dfs(it.first, it.second) } + 1

    return dp[row][col]
}

fun main() {
    input()
    solve()
}