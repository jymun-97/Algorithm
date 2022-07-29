import java.util.*

const val MOD = 1_000_000

var n = 0
var num = StringBuilder()

lateinit var dp : IntArray

fun input() = with(System.`in`.bufferedReader()) {
    num.append(readLine())

    n = num.length
    dp = IntArray(n)
}

fun solve() {
    if (n == 0 || num[0] == '0') {
        println(0)
        return
    }

    dp[0] = 1
    if (n > 1) {
        when {
            num[1] == '0' -> dp[1] = if (get2Digit(1) <= 26) 1 else 0

            else -> dp[1] = if (get2Digit(1) <= 26) 2 else 1
        }
    }

    for (i in 2 until n) {
        if (num[i] == '0') {
            if (num[i - 1] !in '1' .. '2') {
                println(0)
                return
            }
            dp[i] = dp[i - 2]
        }
        else {
            if (num[i - 1] == '0') dp[i] = dp[i - 1]
            else dp[i] = (dp[i - 1] + if (get2Digit(i) <= 26) dp[i - 2] else 0) % MOD
        }
    }

    println(dp.last())
}

fun get2Digit(i: Int) = (num[i - 1] - '0') * 10 + (num[i] - '0')

fun main() {
    input()
    solve()
}