import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer
import kotlin.math.min

var n = 0
var m = 0
lateinit var map: Array<IntArray>
lateinit var dist: Array<Array<IntArray>>

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    map  = Array(n + 1) { IntArray(m + 1) }
    dist = Array(2) { Array(n + 1) { IntArray(m + 1) { Int.MAX_VALUE } } }

    for (i in 1 .. n) {
        readLine().forEachIndexed { j, c ->
            map[i][j + 1] = c.digitToInt()
        }
    }
}

fun solve() {
    val dir = arrayOf(
        arrayOf(1, 0), arrayOf(-1, 0), arrayOf(0, 1), arrayOf(0, -1)
    )
    val que = LinkedList<Int>()
    que.add(1)
    que.add(1)
    que.add(0)
    dist[0][1][1] = 1
    dist[1][1][1] = 1

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        val flag = que.poll()

        for (i in 0 until 4) {
            val nr = row + dir[i][0]
            val nc = col + dir[i][1]

            if (nr < 1 || nr > n || nc < 1 || nc > m) continue
            if (flag == 1 && map[nr][nc] == 1) continue

            val nf = if (map[nr][nc] == 1) 1 else flag
            if (dist[nf][nr][nc] != Int.MAX_VALUE) continue

            que.add(nr)
            que.add(nc)
            que.add(nf)
            dist[nf][nr][nc] = dist[flag][row][col] + 1
        }
    }

    val answer = min(dist[0][n][m], dist[1][n][m])
    println(if (answer == Int.MAX_VALUE) -1 else answer)
}

fun main() {
    input()
    solve()
}