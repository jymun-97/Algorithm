import java.util.*

data class Edge(
    val from: Int,
    val to: Int,
    val weight: Int
)

var n = 0
var m = 0
lateinit var edges: Array<Edge>
lateinit var parentOf: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    parentOf = IntArray(n + 1) { it }
    edges = Array(m) {
        with(readLine().split(' ').map { it.toInt() }) {
            Edge(this[0], this[1], this[2])
        }
    }.sortedBy { it.weight }.toTypedArray()
}

fun solve() {
    var ans = 0

    edges.forEach { edge ->
        if (union(edge.from, edge.to)) {
            ans += edge.weight
        }
    }

    println(ans)
}

fun find(node: Int) : Int {
    if (parentOf[node] == node) return node

    parentOf[node] = find(parentOf[node])
    return parentOf[node]
}

fun union(a: Int, b: Int): Boolean {
    val A = find(a)
    val B = find(b)

    if (A == B) return false
    when {
        A < B -> parentOf[B] = A
        else -> parentOf[A] = B
    }
    return true
}

fun main() {
    input()
    solve()
}