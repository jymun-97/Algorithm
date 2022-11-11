import java.util.*
import kotlin.math.max

var n = 0
lateinit var nums: IntArray
lateinit var dp: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine().split(" ").map { it.toInt() }.toIntArray()
    dp = Array(n) { intArrayOf(0, 0) }
}

fun solve() {
    dp[0][0] = Int.MIN_VALUE
    dp[0][1] = nums[0]

    for (i in 1 until n) {
        dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])
        dp[i][1] = max(nums[i], nums[i] + dp[i - 1][1])
    }

    println(dp.maxOf { it.maxOrNull()!! })
}

fun main() {
    input()
    solve()
}