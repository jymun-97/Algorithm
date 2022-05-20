import java.util.*
import kotlin.collections.HashSet

data class Info(
    val from: Int,
    val dist: Int
)
data class Edge(
    val to: Int,
    val weight: Int
)

val br = System.`in`.bufferedReader()
val sb = StringBuilder()

var n = 0
var m = 0
var t = 0
var w = 0
var start = 0

lateinit var graph : Array<ArrayList<Edge>>
lateinit var cands : ArrayList<Int>
lateinit var dist : Array<IntArray>
lateinit var passed : List<Int>

fun input() = with(br) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        t = nextToken().toInt()
    }
    StringTokenizer(readLine()).apply {
        start = nextToken().toInt()
        passed = listOf(
            nextToken().toInt(),
            nextToken().toInt()
        )
    }
    graph = Array(n + 1) { arrayListOf() }
    dist = Array(3) { IntArray(n + 1) { Int.MAX_VALUE } }
    cands = arrayListOf()

    for (i in 0 until m) {
        StringTokenizer(readLine()).apply {
            val from = nextToken().toInt()
            val to = nextToken().toInt()
            val weight = nextToken().toInt()

            if ((from == passed[0] || from == passed[1]) && (to == passed[0] || to == passed[1])) w = weight

            graph[from].add(Edge(to, weight))
            graph[to].add(Edge(from, weight))
        }
    }

    for (i in 0 until t) cands.add(readLine().toInt())
    cands.sort()
}

fun solve() {
    dijkstra(0, start)
    dijkstra(1, passed[0])
    dijkstra(2, passed[1])

    cands.forEach { cand ->
        val d1 = dist[0][passed[0]] + w + dist[2][cand]
        val d2 = dist[0][passed[1]] + w + dist[1][cand]

        if (dist[0][cand] == d1 || dist[0][cand] == d2) {
            sb.append(cand).append(' ')
        }
    }
    sb.append('\n')
}

fun dijkstra(flag: Int, start: Int) {
    val que = PriorityQueue<Info>() { o1, o2 -> o1.dist - o2.dist }
    que.add(Info(start, 0))
    dist[flag][start] = 0

    while (que.isNotEmpty()) {
        val info = que.poll()

        if (dist[flag][info.from] < info.dist) continue

        graph[info.from].forEach { e ->
            val next = dist[flag][info.from] + e.weight

            if (dist[flag][e.to] > next) {
                que.add(Info(e.to, next))
                dist[flag][e.to] = next
            }
        }
    }
}

fun main() = with(br) {
    val T = readLine().toInt()

    repeat(T) {
        input()
        solve()
    }

    println(sb)
}