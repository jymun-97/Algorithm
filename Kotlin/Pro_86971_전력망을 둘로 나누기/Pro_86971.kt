import java.util.*
import kotlin.math.*

class Solution {
    var n = 0
    var answer = 0
    lateinit var graph: Array<HashSet<Int>>
    lateinit var visit: BooleanArray

    fun solution(n: Int, wires: Array<IntArray>): Int {
        this.n = n
        graph = Array(n + 1) { hashSetOf() }
        visit = BooleanArray(n + 1) { false }

        wires.forEach { wire ->
            val from = wire[0]
            val to = wire[1]

            graph[from].add(to)
            graph[to].add(from)
        }

        answer = n
        val root = 1
        dfs(root)

        return answer
    }

    fun dfs(from: Int): Int {
        visit[from] = true
        var count = 0 // 자식 노드의 개수

        graph[from].forEach { to ->
            if (visit[to]) return@forEach
            count += dfs(to) // 자식 노드의 개수를 누적함
        }

        answer = min(answer, abs(n - 2 * count))
        return count
    }
}