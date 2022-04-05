import java.io.BufferedReader
import java.io.InputStreamReader

var n = 0
lateinit var dp: IntArray

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine().toInt()
    dp = IntArray(n + 1)
}

fun solve() {
    if (n % 2 == 1) {
        print(0)
        return
    }
    dp[0] = 1
    dp[2] = 3
    for (i in 4..n step 2) {
        dp[i] = dp[i - 2] * dp[2]

        for (j in 2 .. i - 4 step 2) {
            dp[i] += 2 * dp[j]
        }

        dp[i] += 2
    }

    print(dp[n])
}

fun main() {
    input()
    solve()
}