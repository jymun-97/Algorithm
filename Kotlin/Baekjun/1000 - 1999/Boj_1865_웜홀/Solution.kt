
data class Edge(
    val from: Int,
    val to: Int,
    val weight: Int
)

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

var n = 0
var m = 0
var w = 0
val edges = mutableListOf<Edge>()
lateinit var dist: IntArray

fun input() = with(reader) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
        w = it[2]
    }
    edges.clear()
    dist = IntArray(n + 1)

    repeat(m) {
        val (from, to, weight) = readLine().split(" ").map { it.toInt() }
        edges.add(Edge(from, to, weight))
        edges.add(Edge(to, from, weight))
    }
    repeat(w) {
        val (from, to, weight) = readLine().split(" ").map { it.toInt() }
        edges.add(Edge(from, to, -weight))
    }
}

fun solve() {
    repeat(n) { count ->
        edges.forEach {
            val next = dist[it.from] + it.weight
            if (dist[it.to] <= next) return@forEach

            dist[it.to] = next
            if (count == n - 1) {
                builder.appendLine("YES")
                return
            }
        }
    }

    builder.appendLine("NO")
}

fun main() = with(reader) {
    repeat(readLine().toInt()) {
        input()
        solve()
    }
    println(builder)
}