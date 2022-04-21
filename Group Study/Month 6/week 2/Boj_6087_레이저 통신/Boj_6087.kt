import java.io.*
import java.lang.Integer.min
import java.util.*

data class Info(
    val row: Int,
    val col: Int,
    val dir: Int,
    val cnt: Int
)
var r = 0
var c = 0
var sr = -1
var sc = -1
var er = -1
var ec = -1

lateinit var map : Array<StringBuilder>
lateinit var count: Array<IntArray>

val direction = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        c = nextToken().toInt()
        r = nextToken().toInt()
    }
    map = Array(r) { StringBuilder() }
    count = Array(r) { IntArray(c) { Int.MAX_VALUE } }

    for (i in 0 until r) {
        map[i].append(readLine())
        for (j in 0 until c) {
            if (map[i][j] == 'C') {
                when (sr) {
                    -1 -> {
                        sr = i
                        sc = j
                    }
                    else -> {
                        er = i
                        ec = j
                    }
                }
            }
        }
    }
}

fun solve() {
    val que = PriorityQueue<Info>() { o1, o2 -> o1.cnt - o2.cnt }
    for (dir in 0 until 4) que.add(Info(sr, sc, dir, 0))
    count[sr][sc] = 0

    while (que.isNotEmpty()) {
        val info = que.poll()

        if (info.row == er && info.col == ec) break
        if (info.cnt > count[info.row][info.col]) continue

        for (nd in 0 until 4) {
            if (!isValidDirection(info.dir, nd)) continue

            val nr = info.row + direction[nd][0]
            val nc = info.col + direction[nd][1]
            val cnt = if (info.dir == nd) info.cnt else info.cnt + 1

            if (nr < 0 || nr >= r || nc < 0 || nc >= c) continue
            if (map[nr][nc] == '*') continue
            if (cnt > count[nr][nc]) continue

            que.add(Info(nr, nc, nd, cnt))
            count[nr][nc] = cnt
        }
    }

    print(count[er][ec])
}

fun isValidDirection(d: Int, nd: Int) : Boolean {
    return when {
        d == 0 && nd == 1 -> false
        d == 1 && nd == 0 -> false
        d == 2 && nd == 3 -> false
        d == 3 && nd == 2 -> false

        else -> true
    }
}

fun main() {
    input()
    solve()
}