class Solution {
    fun solution(distance: Int, rocks: IntArray, n: Int): Int {
        rocks.apply {
            plus(distance)
            sort()
        }

        var ans = 0
        var left = 0
        var right = distance

        while (left <= right) {
            val mid = (left + right) / 2

            var target = 0
            var count = 0
            rocks.forEach {
                if (it - target < mid) count++
                else target = it
            }

            if (count > n) right = mid - 1
            else {
                ans = mid
                left = mid + 1
            }
        }
        return ans
    }
}