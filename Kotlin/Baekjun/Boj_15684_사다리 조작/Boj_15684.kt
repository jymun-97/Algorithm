import java.util.*

var n = 0
var m = 0
var h = 0
val isValid = booleanArrayOf(false, false, false, false)
lateinit var graph: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        h = nextToken().toInt()
    }
    graph = Array(h + 1) { IntArray(n + 1) }
    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        graph[a][b] = b + 1
        graph[a][b + 1] = b
    }
}

fun solve() {
    dfs(0, 1, 0, true)
    println(isValid.indexOfFirst { it })
}

fun move(row: Int, col: Int, flag: Boolean): Int = when {
    row > h -> col

    graph[row][col] == 0 || flag -> move(row + 1, col, false)

    else -> move(row, graph[row][col], true)
}

fun check(): Boolean = (1..n).all { move(0, it, false) == it }

fun dfs(k: Int, preRow: Int, preCol: Int, flag: Boolean) {
    if (k == 4) return
    if (flag && !isValid[k]) isValid[k] = check()
    if (isValid[k]) return

    for (row in preRow..h) {
        for (col in (if (row == preRow) preCol + 1 else 1) until n) {
            if (graph[row][col] != 0 || graph[row][col + 1] != 0) continue

            graph[row][col] = col + 1
            graph[row][col + 1] = col

            dfs(k + 1, row, col, true)

            graph[row][col] = 0
            graph[row][col + 1] = 0

            dfs(k, row, col, false)
        }
    }
}

fun main() {
    input()
    solve()
}