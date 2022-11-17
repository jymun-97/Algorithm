import java.util.*
import kotlin.collections.ArrayList

var n = 0
var ans = 0
lateinit var graph: Array<ArrayList<Int>>
lateinit var isIndoor: BooleanArray
lateinit var checked: BooleanArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    isIndoor = "0${readLine()}".toCharArray().map { it == '1' }.toBooleanArray()
    checked = BooleanArray(n + 1)
    graph = Array(n + 1) { arrayListOf() }

    repeat(n - 1) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        graph[from].add(to)
        graph[to].add(from)

        if (isIndoor[from] && isIndoor[to]) ans += 2
    }
}

fun solve() {
    for (i in 1 .. n) {
        if (!isIndoor[i] && !checked[i]) {
            val targets = bfs(i)
            ans += targets * (targets - 1)
        }
    }
    println(ans)
}

fun bfs(start: Int): Int {
    var count = 0
    val visit = BooleanArray(n + 1)
    val que = LinkedList<Int>()
    que.add(start)
    visit[start] = true
    checked[start] = true

    while (que.isNotEmpty()) {
        val from = que.poll()

        if (isIndoor[from]) {
            count++
            continue
        }

        graph[from].forEach { to ->
            if (visit[to]) return@forEach

            que.add(to)
            visit[to] = true
            checked[to] = true
        }
    }
    return count
}

fun main() {
    input()
    solve()
}