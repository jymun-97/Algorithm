var n = 0
var m = 0
lateinit var board: Array<IntArray>
lateinit var dp: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    board = Array(n) { readLine().map { it.digitToInt() }.toIntArray() }
    dp = Array(n) { IntArray(m) }
}

fun solve() {
    for (i in 0 until m) {
        dp[0][i] = board[0][i]
    }
    for (i in 0 until n) {
        dp[i][0] = board[i][0]
    }
    for (i in 1 until n) {
        for (j in 1 until m) {
            if (board[i][j] == 0) dp[i][j] = 0
            else {
                dp[i][j] = minOf(
                    dp[i - 1][j - 1],
                    dp[i - 1][j],
                    dp[i][j - 1]
                ) + 1
            }
        }
    }

    val max = dp.maxOf { it.maxOf { it } }
    println(max * max)
}

fun main() {
    input()
    solve()
}