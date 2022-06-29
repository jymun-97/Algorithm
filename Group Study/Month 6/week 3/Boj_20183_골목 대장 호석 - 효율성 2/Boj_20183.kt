import java.io.*
import java.util.*
import kotlin.collections.HashSet

data class Info(
    val from: Int,
    val dist: Long
)
data class Edge(
    val to: Int,
    val weight: Long
)
var n = 0
var m = 0
var start = 0
var end = 0
var c = 0L

lateinit var graph: Array<ArrayList<Edge>>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        start = nextToken().toInt()
        end = nextToken().toInt()
        c = nextToken().toLong()
    }

    graph = Array(n + 1) { arrayListOf() }

    for (i in 0 until m) {
        StringTokenizer(readLine()).apply {
            val from = nextToken().toInt()
            val to = nextToken().toInt()
            val weight = nextToken().toLong()

            graph[from].add(Edge(to, weight))
            graph[to].add(Edge(from, weight))
        }
    }
}

fun solve() {
    var left = 1L
    var right = c
    var ans = -1L

    while (left <= right) {
        val mid = (left + right) / 2

        if (isPossible(mid)) {
            ans = mid
            right = mid - 1
        }
        else left = mid + 1
    }

    println(ans)
}

fun isPossible(limit: Long) : Boolean {
    val dist = LongArray(n + 1) { Long.MAX_VALUE }
    val que = PriorityQueue<Info>() { o1, o2 -> (o1.dist - o2.dist).toInt() }
    que.add(Info(start, 0))
    dist[start] = 0

    while (que.isNotEmpty()) {
        val info = que.poll()

        if (info.dist > dist[info.from]) continue

        graph[info.from].forEach { e ->
            if (e.weight > limit) return@forEach

            val next = dist[info.from] + e.weight
            if (next < dist[e.to]) {
                que.add(Info(e.to, next))
                dist[e.to] = next
            }
        }
    }

    return dist[end] <= c
}

fun main() {
    input()
    solve()
}
