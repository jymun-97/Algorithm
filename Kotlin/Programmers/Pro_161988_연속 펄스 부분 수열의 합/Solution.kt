class Solution {
    fun solution(sequence: IntArray): Long = maxOf(
        getMaxSum(sequence.mapIndexed { i, num -> if (i % 2 == 0) num else num * -1 }),
        getMaxSum(sequence.mapIndexed { i, num -> if (i % 2 == 1) num else num * -1 }),
    )

    fun getMaxSum(nums: List<Int>): Long {
        var max = 0L
        var sum = 0L

        nums.forEach {
            sum = maxOf(0, sum + it)
            max = maxOf(max, sum)
        }

        return max
    }
}