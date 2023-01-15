class Solution {
    fun solution(topping: IntArray): Int {
        var ans = 0
        val left = hashMapOf<Int, Int>()
        val right = hashMapOf<Int, Int>()

        topping.forEach { target ->
            right[target] = (right[target] ?: 0) + 1
        }
        topping.forEach { target ->
            left[target] = (left[target] ?: 0) + 1
            right[target] = right[target]!! - 1

            if (right[target]!! == 0) {
                right.remove(target)
            }
            if (left.size == right.size) {
                ans++
            }
        }

        return ans
    }
}