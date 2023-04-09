import java.util.LinkedList

var n = 0
var m = 0
lateinit var graph: Array<HashSet<Int>>
lateinit var visit: BooleanArray
lateinit var targets: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    m = readLine().toInt()
    graph = Array(n + 1) { hashSetOf() }
    visit = BooleanArray(n + 1)

    repeat(n) {
        val from = it + 1
        readLine().split(" ").map { it.toInt() }.forEachIndexed { i, flag ->
            val to = i + 1
            if (flag == 1) {
                graph[from].add(to)
                graph[to].add(from)
            }
        }
    }

    targets = readLine().split(" ").map { it.toInt() }.toIntArray()
}

fun solve() {
    bfs()
    println(
        if (targets.all { visit[it] }) "YES" else "NO"
    )
}

fun bfs() {
    val que = LinkedList<Int>()
    que.add(targets[0])
    visit[targets[0]] = true

    while (que.isNotEmpty()) {
        val from = que.poll()

        graph[from].forEach { to ->
            if (!visit[to]) {
                que.add(to)
                visit[to] = true
            }
        }
    }
}

fun main() {
    input()
    solve()
}