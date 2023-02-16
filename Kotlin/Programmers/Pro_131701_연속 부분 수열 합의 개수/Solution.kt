class Solution {
    fun solution(elements: IntArray): Int {
        val n = elements.size
        val set = hashSetOf<Int>()

        for (i in 0 until n) {
            var sum = elements[i]
            set.add(sum)

            for (size in 1 until n) {
                sum += elements[(i + size) % n]
                set.add(sum)
            }
        }

        return set.size
    }
}