import java.lang.Integer.max
import java.util.*

var n = 0
var m = 0
var h = 0
var ans = 0

lateinit var map: Array<Array<IntArray>>
lateinit var dist: Array<Array<IntArray>>

val dir = arrayOf(
    arrayOf(1, 0, 0),
    arrayOf(-1, 0, 0),
    arrayOf(0, 1, 0),
    arrayOf(0, -1, 0),
    arrayOf(0, 0, 1),
    arrayOf(0, 0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        m = nextToken().toInt()
        n = nextToken().toInt()
        h = nextToken().toInt()
    }
    map = Array(h) { Array(n) { readLine().split(' ').map { it.toInt() }.toIntArray() } }
    dist = Array(h) { Array(n) { IntArray(m) { -1 } } }
}

fun solve() {
    var ans = 0

    bfs()
    for (i in 0 until h) {
        for (j in 0 until n) {
            for (k in 0 until m) {
                if (map[i][j][k] == 0) {
                    if (dist[i][j][k] == -1) {
                        println(-1)
                        return
                    }
                    ans = max(ans, dist[i][j][k])
                }
            }
        }
    }

    println(ans)
}

fun bfs() {
    val que = LinkedList<Int>()

    for (i in 0 until h) {
        for (j in 0 until n) {
            for (k in 0 until m) {
                if (map[i][j][k] == 1) {
                    que.add(i)
                    que.add(j)
                    que.add(k)
                    dist[i][j][k] = 0
                }
            }
        }
    }

    while (que.isNotEmpty()) {
        val height = que.poll()
        val row = que.poll()
        val col = que.poll()

        dir.forEach {
            val nh = height + it[0]
            val nr = row + it[1]
            val nc = col + it[2]

            if (inRange(nh, nr, nc) && dist[nh][nr][nc] == -1 && map[nh][nr][nc] == 0) {
                que.add(nh)
                que.add(nr)
                que.add(nc)

                dist[nh][nr][nc] = dist[height][row][col] + 1
            }
        }
    }
}

fun inRange(height: Int, row: Int, col: Int) = height in 0 until h && row in 0 until n && col in 0 until m

fun main() {
    input()
    solve()
}