class Solution {
    fun solution(a: IntArray, b: IntArray): Int {
        val n = a.size
        var ans = 0

        for (i in 0 until n) {
            ans += a[i] * b[i]
        }

        return ans
    }
}