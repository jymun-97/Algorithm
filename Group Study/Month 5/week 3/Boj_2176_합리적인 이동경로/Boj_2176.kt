import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

data class Info(
    var idx: Int,
    var dist: Int
)
data class Edge(
    var to: Int,
    var weight: Int
)

var n = 0
var m = 0
lateinit var graph: Array<ArrayList<Edge>>
lateinit var dist: IntArray
lateinit var dp: IntArray

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()

    graph = Array(n + 1) { arrayListOf() }
    dist = IntArray(n + 1) { Int.MAX_VALUE }
    dp = IntArray(n + 1)

    while (m-- > 0) {
        readLine().split(' ')
            .also {
                val from = it[0].toInt()
                val to = it[1].toInt()
                val weight = it[2].toInt()

                graph[from].add(Edge(to, weight))
                graph[to].add(Edge(from, weight))
            }
    }
}

fun solve() {
    val que = PriorityQueue<Info>() { o1, o2 -> o1.dist - o2.dist }
    que.add(Info(2, 0))
    dist[2] = 0
    dp[2] = 1

    while (!que.isEmpty()) {
        val info = que.poll()
        val from = info.idx

        if (dist[from] < info.dist) continue

        graph[from].forEach { e ->
            if (dist[e.to] > dist[from] + e.weight) {
                dist[e.to] = dist[from] + e.weight
                que.add(Info(e.to, dist[e.to]))
            }
            if (dist[e.to] > dist[from]) dp[e.to] += dp[from]
        }
    }

    print(dp[1])
}

fun main() {
    input()
    solve()
}