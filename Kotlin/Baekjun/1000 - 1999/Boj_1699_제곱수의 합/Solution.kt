import kotlin.math.min

var n = 0
val nums = arrayListOf<Int>()
lateinit var dp: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    var num = 1
    while (num * num <= n) {
        nums.add(num * num)
        num++
    }
    dp = IntArray(n + 1) { Int.MAX_VALUE }
}

fun solve() {
    dp[0] = 0
    dp[1] = 1
    for (target in 2..n) {
        for (i in nums.lastIndex downTo 0) {
            if (nums[i] > target) continue
            dp[target] = min(
                dp[target],
                dp[target - nums[i]] + 1
            )
        }
    }
    println(dp[n])
}

fun main() {
    input()
    solve()
}