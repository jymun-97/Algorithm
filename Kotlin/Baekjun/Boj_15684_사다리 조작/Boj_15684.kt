import kotlin.collections.HashSet

var n = 0
var m = 0
var k = 0
var min = 4
lateinit var graph: Array<HashSet<Pair<Int, Int>>>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[2]
        m = this[0]
        k = this[1]
    }
    graph = Array(n + 1) { hashSetOf() }

    repeat(k) {
        val (row, col) = readLine().split(" ").map { it.toInt() }
        link(row, col)
    }
}

fun solve() {
    dfs(1, 1, 0, true)
    println(
        if (min == 4) -1
        else min
    )
}

fun link(row: Int, col: Int) = graph[row].apply {
    add(col to col + 1)
    add(col + 1 to col)
}

fun unlink(row: Int, col: Int) = graph[row].apply {
    remove(col to col + 1)
    remove(col + 1 to col)
}

fun goDown(row: Int, col: Int, flag: Boolean): Int = when {
    row > n -> col

    !flag && isLinked(row, col, -1) -> goDown(row, col - 1, true)

    !flag && isLinked(row, col, 1) -> goDown(row, col + 1, true)

    else -> goDown(row + 1, col, false)
}

fun isLinked(row: Int, col: Int) = graph[row].contains(col to col - 1) || graph[row].contains(col to col + 1)
fun isLinked(row: Int, col: Int, dir: Int) = graph[row].contains(col to col + dir)

fun dfs(row: Int, col: Int, count: Int, flag: Boolean) {
    when {
        row > n || count >= min -> return

        flag && isFinish() -> min = count

        col > m -> dfs(row + 1, 1, count, false)

        !isLinked(row, col) && !isLinked(row, col + 1) -> {
            dfs(row, col + 1, count, false)
            link(row, col)
            dfs(row, col + 1, count + 1, true)
            unlink(row, col)
        }

        else -> dfs(row, col + 1, count, false)
    }
}

fun isFinish() = (1..m).all { it == goDown(1, it, false) }

fun main() {
    input()
    solve()
}