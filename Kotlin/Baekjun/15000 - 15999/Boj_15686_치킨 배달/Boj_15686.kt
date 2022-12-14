import java.util.*
import kotlin.collections.HashSet
import kotlin.math.min

data class Node(
    val row: Int,
    val col: Int,
)

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)
var ans = Int.MAX_VALUE
var n = 0
var m = 0
val home = hashMapOf<Node, Int>()
val chicken = hashMapOf<Node, Int>()
lateinit var board: Array<IntArray>
lateinit var dist: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    for (i in 0 until n) {
        for (j in 0 until n) {
            when (board[i][j]) {
                1 -> home[Node(i, j)] = home.size

                2 -> chicken[Node(i, j)] = chicken.size
            }
        }
    }
    dist = Array(home.size) { IntArray(chicken.size) }
}

fun solve() {
    home.keys.forEach {
        bfs(it)
    }
    dfs(0, hashSetOf())

    println(ans)
}

fun dfs(k: Int, selected: HashSet<Int>) {
    if (selected.size > m) return
    if (k == chicken.size) {
        val sum = dist.sumOf {
            it.filterIndexed { index, _ -> selected.contains(index) }.minOrNull() ?: 100
        }
        ans = min(ans, sum)
        return
    }
    dfs(k + 1, selected)
    selected.add(k)
    dfs(k + 1, selected)
    selected.remove(k)
}

fun bfs(start: Node) {
    val visit = Array(n) { BooleanArray(n) }
    val que = LinkedList<Int>().apply {
        add(start.row)
        add(start.col)
        add(0)
    }
    visit[start.row][start.col] = false

    while (que.isNotEmpty()) {
        val from = Node(que.poll(), que.poll())
        val d = que.poll()

        chicken[from]?.let {
            dist[home[start]!!][it] = d
        }

        dir.forEach {
            val nr = from.row + it[0]
            val nc = from.col + it[1]

            if (nr !in 0 until n || nc !in 0 until n) return@forEach
            if (visit[nr][nc]) return@forEach

            que.apply {
                add(nr)
                add(nc)
                add(d + 1)
                visit[nr][nc] = true
            }
        }
    }
}

fun main() {
    input()
    solve()
}