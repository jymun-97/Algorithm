import java.io.*
import java.util.*
import kotlin.math.min

var n = 0
lateinit var graph: Array<ArrayList<Int>>
lateinit var dp: Array<IntArray>

fun input() = with(BufferedReader(InputStreamReader(java.lang.System.`in`))) {
    n = readLine().toInt()
    graph = Array(n + 1) { arrayListOf() }
    dp = Array(n + 1) { intArrayOf(0, 1) }

    for (i in 0 until n - 1) {
        val st = StringTokenizer(readLine())
        val from = st.nextToken().toInt()
        val to = st.nextToken().toInt()

        graph[from].add(to)
        graph[to].add(from)
    }
}

fun solve() {
    bfs(1)
    dfs(1)

    println(min(dp[1][0], dp[1][1]))
}

fun bfs(root: Int) {
    val que = LinkedList<Int>()
    que.add(root)

    while (!que.isEmpty()) {
        val from = que.poll()

        graph[from].forEach { to ->
            graph[to].remove(from)
            que.add(to)
        }
    }
}

fun dfs(from: Int)  {
    graph[from].forEach { to ->
        dfs(to)

        dp[from][0] += dp[to][1]
        dp[from][1] += min(dp[to][0], dp[to][1])
    }
}


fun main() {
    input()
    solve()
}