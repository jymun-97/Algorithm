import kotlin.math.max

class Solution {
    fun solution(n: Int, left: Long, right: Long) = IntArray((right - left + 1).toInt()) {
        max(
            (it + left) / n + 1,
            (it + left) % n + 1
        ).toInt()
    }
}