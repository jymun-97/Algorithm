
data class Edge(
    val to: Int,
    val dist: Int
)

var n = 0
var ans = 0
lateinit var graph: Array<MutableList<Edge>>
lateinit var visit: BooleanArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    graph = Array(n + 1) { mutableListOf() }
    visit = BooleanArray(n + 1)

    repeat(n - 1) {
        val (from, to, weight) = readLine().split(" ").map { it.toInt() }

        graph[from].add(Edge(to, weight))
        graph[to].add(Edge(from, weight))
    }
}

fun solve() {
    visit[1] = true
    dfs(1)

    println(ans)
}

fun dfs(from: Int): Int {
    val result = graph[from].filter { !visit[it.to] }
        .map { e ->
            visit[e.to] = true
            (e.dist + dfs(e.to)).also {
                ans = maxOf(ans, it)
                visit[e.to] = false
            }
        }
        .sortedDescending()

    if (result.size > 1) ans = maxOf(ans, result[0] + result[1])

    return if (result.isEmpty()) 0 else result[0]
}

fun main() {
    input()
    solve()
}