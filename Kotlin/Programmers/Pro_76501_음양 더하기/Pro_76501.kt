class Solution {
    fun solution(absolutes: IntArray, signs: BooleanArray): Int {
        val n = absolutes.size
        var sum = 0

        for (i in 0 until n) {
            sum += if (signs[i]) absolutes[i] else -absolutes[i]
        }

        return sum
    }
}