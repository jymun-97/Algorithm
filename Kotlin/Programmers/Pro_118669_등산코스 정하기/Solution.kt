import java.util.PriorityQueue

data class Info(
    val from: Int,
    val dist: Int
)
data class Edge(
    val to: Int,
    val weight: Int
)

class Solution {
    var n = 0
    val gates = hashSetOf<Int>()
    val summits = hashSetOf<Int>()
    val ans = intArrayOf(Int.MAX_VALUE, Int.MAX_VALUE)
    lateinit var graph: Array<HashSet<Edge>>
    lateinit var dist: IntArray

    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        this.n = n
        this.gates.addAll(gates.toList())
        this.summits.addAll(summits.toList())

        graph = Array(n + 1) { hashSetOf() }

        paths.forEach {
            val (from, to, weight) = it
            graph[from].add(Edge(to, weight))
            graph[to].add(Edge(from, weight))
        }

        gates.forEach { bfs(it) }
        return ans
    }

    fun bfs(start: Int) {
        val dist = IntArray(n + 1) { Int.MAX_VALUE }
        val que = PriorityQueue<Info>() { i1, i2 -> i1.dist - i2.dist }
        que.add(Info(start, 0))
        dist[start] = 0

        while (que.isNotEmpty()) {
            val (from, intensity) = que.poll()

            if (intensity > dist[from] || intensity > ans[1]) continue
            if (summits.contains(from)) {
                when {
                    ans[1] > intensity -> {
                        ans[0] = from
                        ans[1] = intensity
                    }
                    ans[1] == intensity -> ans[0] = minOf(from, ans[0])
                }
                continue
            }

            graph[from].forEach {
                val (to, weight) = it

                val next = maxOf(dist[from], weight)
                if (dist[to] <= next || gates.contains(to) || next > ans[1]) return@forEach

                que.add(Info(to, next))
                dist[to] = next
            }
        }
    }
}