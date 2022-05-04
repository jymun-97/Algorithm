import java.util.TreeSet

class Solution {
    fun solution(numbers: IntArray): IntArray {
        val n = numbers.size
        val set = TreeSet<Int>()

        for (i in 0 until n - 1) {
            for (j in i + 1 until n) {
                set.add(numbers[i] + numbers[j])
            }
        }

        return set.toIntArray()
    }
}