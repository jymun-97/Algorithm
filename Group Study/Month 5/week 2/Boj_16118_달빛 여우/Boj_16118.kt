import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread
import kotlin.math.min

data class Info(
    val idx: Int,
    val dist: Int,
    val flag: Int = 0
)
data class Edge(
    val to: Int,
    val weight: Int
)

var n = 0
var m = 0
lateinit var graph: Array<ArrayList<Edge>>
lateinit var dist_fox: IntArray
lateinit var dist_wolf: Array<IntArray>

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    StringTokenizer(readLine()).also {
        n = it.nextToken().toInt()
        m = it.nextToken().toInt()
    }

    graph = Array(n + 1) { arrayListOf() }
    dist_fox = IntArray(n + 1) { Int.MAX_VALUE }
    dist_wolf = Array(2) { IntArray(n + 1) { Int.MAX_VALUE } }

    while (m-- > 0) {
        StringTokenizer(readLine()).also {
            val from = it.nextToken().toInt()
            val to = it.nextToken().toInt()
            val weight = it.nextToken().toInt() * 2

            graph[from].add(Edge(to, weight))
            graph[to].add(Edge(from, weight))
        }
    }
}

fun solve() {
    thread { dijkstraFox() }
    thread { dijkstraWolf() }.join()

    var answer = 0
    for (i in 1..n) {
        if (dist_fox[i] < min(dist_wolf[0][i], dist_wolf[1][i])) {
            answer++
        }
    }
    println(answer)
}

fun dijkstraFox() {
    val que = PriorityQueue<Info>() { o1, o2 -> o1.dist - o2.dist }
    que.add(Info(1, 0))
    dist_fox[1] = 0

    while (que.isNotEmpty()) {
        val info = que.poll()
        val from = info.idx
        val dist = info.dist

        if (dist_fox[from] < dist) continue

        graph[from].forEach { e ->
            val nextDist = dist_fox[from] + e.weight
            if (dist_fox[e.to] > nextDist) {
                dist_fox[e.to] = nextDist
                que.add(Info(e.to, nextDist))
            }
        }
    }
}

fun dijkstraWolf() {
    val que = PriorityQueue<Info>() { o1, o2 -> o1.dist - o2.dist }
    que.add(Info(1, 0, 0))
    dist_wolf[0][1] = 0

    while (que.isNotEmpty()) {
        val info = que.poll()
        val from = info.idx
        val dist = info.dist
        val flag = info.flag

        if (dist_wolf[flag][from] < dist) continue

        graph[from].forEach { e ->
            val weight = if (flag == 0) (e.weight / 2) else (e.weight * 2)
            val nextDist = dist_wolf[flag][from] + weight

            if (dist_wolf[1 - flag][e.to] > nextDist) {
                dist_wolf[1 - flag][e.to] = nextDist
                que.add(Info(e.to, nextDist, 1 - flag))
            }
        }
    }
}

fun main() {
    input()
    solve()
}