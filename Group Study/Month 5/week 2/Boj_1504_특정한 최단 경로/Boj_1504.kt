import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.min

data class Info(
    val from: Int,
    val dist: Int
)

data class Edge(
    val to: Int,
    val weight: Int
)
var n = 0
var m = 0
const val MAX = 200_000_000
lateinit var graph : Array<ArrayList<Edge>>
lateinit var points : IntArray

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    graph = Array(n + 1) { arrayListOf() }

    while (m --> 0) {
        StringTokenizer(readLine()).apply {
            val from = nextToken().toInt()
            val to = nextToken().toInt()
            val weight = nextToken().toInt()

            graph[from].add(Edge(to, weight))
            graph[to].add(Edge(from, weight))
        }
    }

    StringTokenizer(readLine()).apply {
        points = intArrayOf(
            nextToken().toInt(),
            nextToken().toInt()
        )
    }
}

fun solve() {
    val dist1 = dijkstra(1, points[0]) + dijkstra(points[0], points[1]) + dijkstra(points[1], n)
    val dist2 = dijkstra(1, points[1]) + dijkstra(points[1], points[0]) + dijkstra(points[0], n)
    val ans = min(dist1, dist2)

    print(
        if (ans >= MAX) -1
        else ans
    )
}

fun dijkstra(start: Int, end: Int): Int {
    val dist = IntArray(n + 1) { MAX }
    val que = PriorityQueue<Info>() { o1, o2 -> o1.dist - o2.dist }
    que.add(Info(start, 0))
    dist[start] = 0

    while (que.isNotEmpty()) {
        val info = que.poll()

        if (info.from == end) break
        if (dist[info.from] < info.dist) continue

        graph[info.from].forEach { e ->
            val next = dist[info.from] + e.weight

            if (dist[e.to] > next) {
                que.add(Info(e.to, next))
                dist[e.to] = next
            }
        }
    }
    return dist[end]
}

fun main() {
    input()
    solve()
}