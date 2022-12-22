import java.util.LinkedList

data class Edge(
    val to: Int,
    val weight: Int = 0
) {
    override fun equals(other: Any?): Boolean = to == (other as Edge).to
    override fun hashCode(): Int = to.hashCode()
}
var n = 0
lateinit var graph: Array<HashSet<Edge>>
lateinit var length: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    graph = Array(n + 1) { hashSetOf() }
    length = IntArray(n + 1)

    repeat(n) {
        val tokens = readLine().split(" ").map { it.toInt() }
        val from = tokens[0]
        for (i in 1 until tokens.size - 1 step 2) {
            val to = tokens[i]
            val weight = tokens[i + 1]

            graph[from].add(Edge(to, weight))
        }
    }
}

fun solve() {
    bfs()
    for (i in 1 .. n) {
        println("$i | ${graph[i].map { it.to }}")
    }
    dfs(1)

    println(length.contentToString())
    println(length.maxOf { it })
}

fun bfs() {
    val que = LinkedList<Int>()
    que.add(1)

    while (que.isNotEmpty()) {
        val from = que.poll()

        graph[from].forEach { e ->
            graph[e.to].remove(Edge(from))
            que.add(e.to)
        }
    }
}

fun dfs(from: Int): Int {
    if (graph[from].size == 0) return 0

    var first = 0
    var second = 0

    graph[from].forEach { e ->
        val cand = e.weight + dfs(e.to)
        when {
            cand > first -> {
                second = first
                first = cand
            }
            cand > second -> second = cand
        }
    }

    length[from] = (first + second)
    return first
}

fun main() {
    input()
    solve()
}