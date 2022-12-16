import kotlin.math.max

var n = 0
var ans = 0
lateinit var nums: List<Int>
lateinit var dp: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine().split(" ").map { it.toInt() }
    dp = IntArray(n) { 1 }
}

fun solve() {
    for (i in 1 until n) {
        for (j in 0 until i) {
            if (nums[i] > nums[j] && dp[i] < dp[j] + 1) {
                dp[i] = dp[j] + 1
            }
        }
    }
    println(dp.maxOf { it })
}

fun main() {
    input()
    solve()
}