data class Edge(
    val from: Int,
    val to: Int,
    val weight: Int,
)

var n = 0
var m = 0
lateinit var edges: List<Edge>
lateinit var dist: LongArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        m = this[1]
    }
    edges = List(m) {
        val (from, to, weight) = readLine().split(" ").map { it.toInt() }
        Edge(from, to, weight)
    }
    dist = LongArray(n + 1) { Long.MAX_VALUE }
}

fun solve() {
    dist[1] = 0
    repeat(n) { count ->
        edges.forEach {
            if (dist[it.from] == Long.MAX_VALUE) return@forEach
            if (dist[it.to] <= dist[it.from] + it.weight) return@forEach

            dist[it.to] = dist[it.from] + it.weight
            if (count == n - 1) {
                println(-1)
                return
            }
        }
    }
    print(
        buildString {
            for (i in 2..n) {
                appendLine(if (dist[i] == Long.MAX_VALUE) -1 else dist[i])
            }
        }
    )
}

fun main() {
    input()
    solve()
}