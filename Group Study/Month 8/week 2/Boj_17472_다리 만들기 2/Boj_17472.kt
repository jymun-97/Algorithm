import java.util.*
import kotlin.math.max

var n = 1
var r = 0
var c = 0
var sum = 0
val connected = hashSetOf<Pair<Int, Int>>()
lateinit var board : Array<IntArray>
lateinit var visit : Array<BooleanArray>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        r = nextToken().toInt()
        c = nextToken().toInt()
    }
    board = Array(r) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    visit = Array(r) { BooleanArray(c) }
}

fun solve() {
    for (i in 0 until r) {
        for (j in 0 until c) {
            if (board[i][j] == 1 && !visit[i][j]) {
                bfs(i, j, n++)
            }
        }
    }
    n--

    dfs(2)
    board.forEach { println(it.contentToString()) }
    println(connected)
    println(if (connected.size == n - 1) sum else -1)
}

fun bfs(sr: Int, sc: Int, index: Int) {
    val dir = arrayOf(
        arrayOf(1, 0),
        arrayOf(-1, 0),
        arrayOf(0, 1),
        arrayOf(0, -1)
    )
    val que = LinkedList<Int>()
    que.add(sr)
    que.add(sc)
    visit[sr][sc] = true
    board[sr][sc] = index

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until r || nc !in 0 until c) return@forEach
            if (visit[nr][nc] || board[nr][nc] != 1) return@forEach

            que.add(nr)
            que.add(nc)
            visit[nr][nc] = true
            board[nr][nc] = index
        }
    }
}

fun dfs(k: Int) {
    if (k >= max(r, c) || connected.size == n - 1) return

    for (i in 0 until r) {
        for (j in 1 until c - k) {
            if (board[i].asList().subList(j, j + k).any { it != 0 }) continue
            if (board[i][j - 1] == 0 || board[i][j + k] == 0) continue

            var a = board[i][j - 1]
            var b = board[i][j + k]
            if (a == b) continue
            if (a > b) a = b.also { b = a }

            if (!connected.contains(a to b)) {
                connected.add(a to b)
                sum += k
            }
            if (connected.size == n - 1) return
        }
    }

    for (i in 0 until c) {
        for (j in 1 until r - k) {
            var flag = false
            for (x in 0 until k) {
                if (board[j + x][i] != 0) {
                    flag = true
                    break
                }
            }
            if (flag || board[j - 1][i] == 0 || board[j + k][i] == 0) continue

            var a = board[j - 1][i]
            var b = board[j + k][i]
            if (a == b) continue
            if (a > b) a = b.also { b = a }

            if (!connected.contains(a to b)) {
                connected.add(a to b)
                sum += k
            }
            if (connected.size == n - 1) return
        }
    }

    dfs(k + 1)
}

fun main() {
    input()
    solve()
}