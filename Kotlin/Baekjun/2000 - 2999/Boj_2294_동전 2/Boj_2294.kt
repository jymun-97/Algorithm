import java.util.*
import kotlin.math.min

var n = 0
var k = 0
lateinit var coins: IntArray
lateinit var dp: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        k = nextToken().toInt()
    }
    coins = IntArray(n) { readLine().toInt() }.sortedDescending().toIntArray()
    dp = IntArray(k + 1) { 1_000_000 }
}

fun solve() {
    dp[0] = 0

    for (i in 1..k) {
        coins.forEach { coin ->
            if (coin <= i && dp[i - coin] != -1) {
                dp[i] = min(dp[i], dp[i - coin] + 1)
            }
        }
    }

    println(if (dp[k] >= 1_000_000) -1 else dp[k])
}

fun main() {
    input()
    solve()
}