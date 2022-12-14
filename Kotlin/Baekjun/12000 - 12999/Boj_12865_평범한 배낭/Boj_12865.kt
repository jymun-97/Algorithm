import java.util.*
import kotlin.math.max

var n = 0
var k = 0
lateinit var dp: Array<IntArray>
lateinit var weights: IntArray
lateinit var values: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    val tokens = readLine().split(" ").map { it.toInt() }
    n = tokens[0]
    k = tokens[1]

    dp = Array(n + 1) { IntArray(k + 1) }
    weights = IntArray(n + 1)
    values = IntArray(n + 1)

    repeat(n) {
        val (weight, value) = readLine().split(" ").map { it.toInt() }
        weights[it + 1] = weight
        values[it + 1] = value
    }
}

fun solve() {
    dp[1].fill(values[1], weights[1])

    for (i in 2..n) {
        for (j in 0..k) {
            dp[i][j] = if (j >= weights[i]) max(
                dp[i - 1][j - weights[i]] + values[i],
                dp[i - 1][j]
            ) else dp[i - 1][j]
        }
    }

    println(dp[n][k])
}

fun main() {
    input()
    solve()
}