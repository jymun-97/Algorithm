
data class Edge(
    val from: Int,
    val to: Int,
    val weight: Int
)
var n = 0
var m = 0
lateinit var edges: List<Edge>
lateinit var parentOf: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    parentOf = IntArray(n + 1) { it }
    edges = List(m) {
        val (from, to, weight) = readLine().split(" ").map { it.toInt() }
        Edge(from, to, weight)
    }.sortedBy { it.weight }
}

fun solve() {
    var count = 0
    var sum = 0
    for (e in edges) {
        if (count == n - 2) break

        val from = find(e.from)
        val to = find(e.to)

        when (from) {
            to -> continue
            else -> {
                parentOf[to] = from
                sum += e.weight

                count++
            }
        }
    }

    println(sum)
}

fun find(target: Int): Int = if (parentOf[target] == target) target else find(parentOf[target]).also {
    parentOf[target] = it
}

fun main() {
    input()
    solve()
}