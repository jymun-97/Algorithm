class Solution {
    fun solution(sequence: IntArray, k: Int): IntArray {
        var left = 0
        var right = 0
        var minSize = sequence.size + 1
        var sum = sequence[left]
        val ans = intArrayOf(left, right)

        while (left < sequence.size) {
            when {
                left > right -> {
                    sum = sequence[left]
                    right = left
                }
                sum < k && right == sequence.lastIndex -> break

                sum < k -> sum += sequence[++right]

                sum > k -> sum -= sequence[left++]

                sum == k -> {
                    val size = right - left + 1
                    if (size < minSize) {
                        minSize = size
                        ans[0] = left
                        ans[1] = right
                    }
                    sum -= sequence[left++]
                }
            }
        }

        return ans
    }
}