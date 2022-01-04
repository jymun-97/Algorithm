class Solution {
    var n = 0
    lateinit var times: IntArray

    fun solution(n: Int, times: IntArray): Long {
        this.n = n
        this.times = times

        var left = 1L
        var right = 1_000_000_000L * 100_000L

        var ans = 0L
        while (left <= right) {
            val mid = (left + right) / 2L

            if (isPossible(mid)) {
                ans = mid
                right = mid - 1L
            }
            else left = mid + 1L
        }

        return ans
    }

    private fun isPossible(target: Long) : Boolean {
        var sum = 0L

        times.forEach { time ->
            sum += target / time
        }

        return sum >= n
    }
}