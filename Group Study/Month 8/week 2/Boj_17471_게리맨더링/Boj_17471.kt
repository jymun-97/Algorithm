import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs
import kotlin.math.min

var n = 0
var ans = Int.MAX_VALUE
lateinit var nums : List<Int>
lateinit var graph : Array<HashSet<Int>>
val a = hashSetOf<Int>()
val b = hashSetOf<Int>()

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine().split(" ").map { it.toInt() }
    graph = Array(n) { hashSetOf() }

    repeat(n) { from ->
        readLine().split(" ").map { it.toInt() - 1 }.forEachIndexed { i, to ->
            if (i == 0) return@forEachIndexed
            graph[from].add(to)
        }
    }
}

fun solve() {
    dfs(0)
    println(if (ans == Int.MAX_VALUE) -1 else ans)
}

fun dfs(k: Int) {
    if (k == n) {
        run()
        return
    }

    a.add(k)
    dfs(k + 1)
    a.remove(k)

    b.add(k)
    dfs(k + 1)
    b.remove(k)
}

fun run() {
    if (a.isEmpty() || b.isEmpty()) return
    if (!bfs(a) || !bfs(b)) return

    val diff = abs(
        nums.filterIndexed { index, _ -> a.contains(index) }.sum() - nums.filterIndexed { index, _ -> b.contains(index) }.sum()
    )

    ans = min(ans, diff)
}

fun bfs(group: HashSet<Int>) : Boolean {
    val visit = hashSetOf<Int>()
    val que = LinkedList<Int>()
    val start = group.random()
    que.add(start)
    visit.add(start)

    while (que.isNotEmpty()) {
        val from = que.poll()

        graph[from].forEach { to ->
            if (visit.contains(to) || !group.contains(to)) return@forEach
            que.add(to)
            visit.add(to)
        }
    }

    return group == visit
}

fun main() {
    input()
    solve()
}