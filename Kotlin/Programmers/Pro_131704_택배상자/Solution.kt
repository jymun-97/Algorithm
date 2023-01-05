import java.util.*

class Solution {
    fun solution(order: IntArray): Int {
        val n = order.size
        val orderOf = IntArray(n + 1)
        order.forEachIndexed { i, box ->
            orderOf[box] = i
        }

        val readyQue = LinkedList((1..n).toList())
        val stack = LinkedList<Int>()
        var target = 0

        while (readyQue.isNotEmpty()) {
            val box = readyQue.poll()
            when {
                orderOf[box] == target -> target++

                stack.isEmpty() -> stack.add(box)

                orderOf[stack.peekLast()] != target -> stack.add(box)

                else -> {
                    while (stack.isNotEmpty() && orderOf[stack.peekLast()] == target) {
                        stack.pollLast()
                        target++
                    }
                    stack.add(box)
                }
            }
        }

        while (stack.isNotEmpty()) {
            val box = stack.pollLast()
            if (orderOf[box] != target) break

            target++
        }

        return target
    }
}