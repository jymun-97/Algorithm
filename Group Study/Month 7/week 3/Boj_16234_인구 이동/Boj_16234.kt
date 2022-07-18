import java.util.*
import kotlin.math.abs

var n = 0
var l = 0
var r = 0
var flag = false
lateinit var board: Array<IntArray>
lateinit var group: Array<IntArray>

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        l = nextToken().toInt()
        r = nextToken().toInt()
    }
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
}

fun solve() {
    var ans = 0

    while (!flag) {
        flag = true

        group = Array(n) { IntArray(n) }
        var value = 1

        for (row in 0 until n) {
            for (col in 0 until n) {
                bfs(row, col, value++)
            }
        }

        ans++
    }

    print(ans - 1)
}

fun bfs(sr: Int, sc: Int, value: Int) {
    if (group[sr][sc] != 0) return

    var sum = 0
    val selected = LinkedList<Int>()
    val que = LinkedList<Int>()
    que.apply {
        add(sr)
        add(sc)
    }.also {
        selected.apply {
            add(sr)
            add(sc)
        }
    }
    group[sr][sc] = value
    sum += board[sr][sc]

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        for (i in 0 until 4) {
            val nr = row + dir[i][0]
            val nc = col + dir[i][1]

            if (nr !in 0 until n || nc !in 0 until n) continue
            if (group[nr][nc] != 0) continue
            if (abs(board[row][col] - board[nr][nc]) !in l..r) continue

            flag = false
            que.apply {
                add(nr)
                add(nc)
            }.also {
                selected.apply {
                    add(nr)
                    add(nc)
                }
            }
            group[nr][nc] = value
            sum += board[nr][nc]
        }
    }

    val next = sum / (selected.size / 2)
    while (selected.isNotEmpty()) {
        board[selected.poll()][selected.poll()] = next
    }
}

fun main() {
    input()
    solve()
}