
var n = 0
var m = 0
var find = false
lateinit var graph: Array<HashSet<Int>>
lateinit var visit: BooleanArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }

    graph = Array(n) { hashSetOf() }
    visit = BooleanArray(n)

    repeat(m) {
        val (from, to) = readLine().split(" ").map { it.toInt() }

        graph[from].add(to)
        graph[to].add(from)
    }
}

fun solve() {
    (0 until n).forEach { from ->
        visit = BooleanArray(n)
        visit[from] = true
        dfs(from, 0)

        if (find) {
            println(1)
            return
        }
    }

    println(0)
}

fun dfs(from: Int, depth: Int) {
    if (depth == 4) {
        find = true
        return
    }

    graph[from].forEach { to ->
        if (visit[to]) return@forEach

        visit[to] = true
        dfs(to, depth + 1)

        if (find) return
        visit[to] = false
    }
}

fun main() {
    input()
    solve()
}