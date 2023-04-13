class Solution {
    var n = 0
    var str = ""
    lateinit var dp: Array<BooleanArray>

    fun solution(s: String): Int {
        n = s.length
        str = s
        dp = Array(n) { BooleanArray(n) }

        init()

        var ans = 0
        for (i in 0 until n) {
            for (j in n - 1 downTo i) {
                val size = j - i + 1
                when {
                    ans >= size -> break

                    !dp[i][j] -> continue

                    else -> ans = size
                }
            }
        }

        return ans
    }

    fun init() {
        for (i in 0 until n) {
            dp[i][i] = true
        }
        for (i in 0 until n - 1) {
            dp[i][i + 1] = str[i] == str[i + 1]
        }
        for (offset in 2 until n) {
            for (i in 0 until n - offset) {
                val j = i + offset
                dp[i][j] = str[i] == str[j] && dp[i + 1][j - 1]
            }
        }
    }
}