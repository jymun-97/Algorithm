import java.util.LinkedList
import kotlin.math.absoluteValue

class Solution {
    var n = 0
    var ans = 0L
    lateinit var graph: Array<HashSet<Int>>
    lateinit var weight: LongArray
    lateinit var indegree: IntArray

    fun solution(a: IntArray, edges: Array<IntArray>): Long {
        n = a.size
        weight = a.map { it.toLong() }.toLongArray()
        indegree = IntArray(n)
        graph = Array(n) { hashSetOf() }

        edges.forEach {
            val (from, to) = it

            graph[from].add(to).also { indegree[to]++ }
            graph[to].add(from).also { indegree[from]++ }
        }

        bfs()

        return if (weight.sum() == 0L) ans else -1
    }

    fun bfs() {
        val que = LinkedList<Int>()
        (0 until n).forEach {
            if (indegree[it] > 1) return@forEach
            que.add(it)
        }

        while (que.isNotEmpty()) {
            val from = que.poll()

            graph[from].forEach { to ->
                ans += weight[from].absoluteValue

                weight[to] += weight[from]
                weight[from] = 0

                graph[to].remove(from)
                if (--indegree[to] == 1) {
                    que.add(to)
                }
            }
        }
    }
}