import java.util.LinkedList

data class Point(
    val row: Int,
    val col: Int
)
var ans = Int.MAX_VALUE
var n = 0
var m = 0
var total = 0
val selected = LinkedList<Point>()
val cands = mutableListOf<Point>()
lateinit var board: Array<IntArray>

val dir = arrayOf(
    intArrayOf(1, 0),
    intArrayOf(-1, 0),
    intArrayOf(0, 1),
    intArrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }

    for (i in 0 until n) {
        for (j in 0 until n) {
            when (board[i][j]) {
                0 -> total++
                2 -> cands.add(Point(i, j))
            }
        }
    }
}

fun solve() {
    dfs(0, -1)
    println(if (ans == Int.MAX_VALUE) -1 else ans)
}

fun dfs(k: Int, pre: Int) {
    if (k == m) {
        bfs()
        return
    }
    for (i in pre + 1 until cands.size) {
        selected.add(cands[i])
        dfs(k + 1, i)
        selected.pollLast()
    }
}

fun bfs() {
    var remain = total
    var max = 0
    val que = LinkedList<Int>()
    val visit = Array(n) { BooleanArray(n) }
    selected.forEach {
        que.add(it.row)
        que.add(it.col)
        que.add(0)

        visit[it.row][it.col] = true
    }

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        val dist = que.poll()

        if (board[row][col] == 0) remain--
        if (remain == 0) {
            max = maxOf(max, dist)
            break
        }
        if (dist >= ans) return

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until n) return@forEach
            if (visit[nr][nc] || board[nr][nc] == 1) return@forEach

            que.apply {
                add(nr)
                add(nc)
                add(dist + 1)

                visit[nr][nc] = true
            }
        }
    }
    if (remain > 0) return
    ans = max
}

fun main() {
    input()
    solve()
}