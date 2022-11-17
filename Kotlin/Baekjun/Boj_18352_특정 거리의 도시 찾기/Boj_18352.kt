import java.util.*

var n = 0
var m = 0
var k = 0
var x = 0
lateinit var graph: Array<ArrayList<Int>>
lateinit var visit: BooleanArray

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        k = nextToken().toInt()
        x = nextToken().toInt()
    }
    graph = Array(n + 1) { arrayListOf() }
    visit = BooleanArray(n + 1)

    repeat(m) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        graph[from].add(to)
    }
}

fun solve() {
    val targets = arrayListOf<Int>()
    val que = LinkedList<Int>().apply {
        add(x)
        add(0)
        visit[x] = true
    }
    while (que.isNotEmpty()) {
        val from = que.poll()
        val dist = que.poll()

        if (dist > k) break
        if (dist == k) {
            targets.add(from)
            continue
        }

        graph[from].forEach { to ->
            if (visit[to]) return@forEach

            que.add(to)
            que.add(dist + 1)
            visit[to] = true
        }
    }
    println(
        if (targets.isEmpty()) -1
        else targets.sorted().joinToString("\n")
    )
}

fun main() {
    input()
    solve()
}