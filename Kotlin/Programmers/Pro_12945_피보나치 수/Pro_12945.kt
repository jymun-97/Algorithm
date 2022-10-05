class Solution {
    var n = 0
    lateinit var dp: IntArray

    fun solution(n: Int): Int {
        this.n = n
        dp = IntArray(n + 1) { -1 }

        dp[0] = 0
        dp[1] = 1

        return fibonacci(n)
    }

    fun fibonacci(num: Int) : Int {
        if (dp[num] != -1) return dp[num]

        dp[num] = (fibonacci(num - 1) + fibonacci(num - 2)) % 1234567
        return dp[num]
    }
}