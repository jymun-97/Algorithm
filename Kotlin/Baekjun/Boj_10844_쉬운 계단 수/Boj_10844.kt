import java.util.*

const val MOD = 1_000_000_000

var n = 0
lateinit var dp: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    dp = Array(n + 1) { IntArray(10) }
}

fun solve() {
    init()
    for (i in 2..n) {
        for (j in 0..9) {
            when (j) {
                0 -> dp[i][j] = dp[i - 1][1]

                9 -> dp[i][j] = dp[i - 1][8]

                else -> dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD
            }
        }
    }

    var ans = dp[n].sumOf { it.toLong() } % MOD
    println(ans)
}

fun init() {
    dp[1].fill(1)
    dp[1][0] = 0
}

fun main() {
    input()
    solve()
}