import java.util.LinkedList

class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        val limit = (queue1.size + queue2.size) * 2

        val a = LinkedList(queue1.toList())
        val b = LinkedList(queue2.toList())
        var sumOfA = a.sumOf { it.toLong() }
        var sumOfB = b.sumOf { it.toLong() }

        if ((sumOfA + sumOfB) % 2 == 1L) return -1

        var ans = 0
        while (sumOfA != sumOfB && ans < limit) {
            if (sumOfA > sumOfB) a.poll().let {
                sumOfA -= it
                sumOfB += it

                b.add(it)
            }
            else b.poll().let {
                sumOfA += it
                sumOfB -= it

                a.add(it)
            }

            ans++
        }

        return if (ans >= limit) -1 else ans
    }
}

fun main() {
    println(
        Solution().solution(
            intArrayOf(3),
            intArrayOf(5)
        )
    )
}