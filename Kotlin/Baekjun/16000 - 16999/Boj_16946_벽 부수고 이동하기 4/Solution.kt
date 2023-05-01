import java.util.LinkedList

data class Point(
    val row: Int,
    val col: Int
)

var n = 0
var m = 0
lateinit var board: Array<IntArray>
lateinit var ans: Array<IntArray>
lateinit var visit: Array<BooleanArray>

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
    board = Array(n) { readLine().map { it.digitToInt() }.toIntArray() }
    ans = Array(n) { i -> IntArray(m) { j -> board[i][j] } }
    visit = Array(n) { BooleanArray(m) }
}

fun solve() {
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (board[i][j] == 0 && !visit[i][j]) {
                bfs(i, j)
            }
        }
    }

    println(ans.joinToString("\n") { it.joinToString("") { (it % 10).toString() } })
}

fun bfs(sr: Int, sc: Int) {
    var area = 1
    val que = LinkedList<Int>().apply {
        add(sr)
        add(sc)

        visit[sr][sc] = true
    }
    val visited = LinkedList<Int>().apply {
        add(sr)
        add(sc)
    }

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until m) return@forEach
            if (visit[nr][nc] || board[nr][nc] == 1) return@forEach

            que.add(nr)
            que.add(nc)
            visited.add(nr)
            visited.add(nc)

            visit[nr][nc] = true
            area++
        }
    }
    val flag = Array(n) { BooleanArray(m) }
    while (visited.isNotEmpty()) {
        val row = visited.poll()
        val col = visited.poll()

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until m) return@forEach
            if (flag[nr][nc] || board[nr][nc] == 0) return@forEach

            ans[nr][nc] += area
            flag[nr][nc] = true
        }
    }
}

fun main() {
    input()
    solve()
}