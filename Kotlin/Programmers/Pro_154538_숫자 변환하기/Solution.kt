import java.util.*

class Solution {
    var num = 0
    var target = 0
    var n = 0
    val visit = hashSetOf<Int>()

    fun solution(x: Int, y: Int, n: Int): Int {
        this.num = x
        this.target = y
        this.n = n

        return bfs()
    }

    fun bfs(): Int {
        val que = LinkedList<Int>()
        que.add(num)
        que.add(0)
        visit.add(num)

        while (que.isNotEmpty()) {
            val from = que.poll()
            val dist = que.poll()

            if (from == target) return dist

            getCandidates(from).forEach { to ->
                if (to > target || visit.contains(to)) return@forEach

                que.add(to)
                que.add(dist + 1)
                visit.add(to)
            }
        }

        return -1
    }

    fun getCandidates(x: Int) = listOf(x + n, x * 2, x * 3)
}