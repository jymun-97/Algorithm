import java.io.*
import java.util.*
import kotlin.collections.HashSet

data class Point(
    val row: Int,
    val col: Int
)

var n = 0
var m = 0
lateinit var set: HashSet<Point>
lateinit var map: Array<IntArray>

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    map = Array(n + 1) { IntArray(m + 1) }
    set = hashSetOf()

    for (i in 1..n) {
        StringTokenizer(readLine()).apply {
            for (j in 1..m) {
                map[i][j] = nextToken().toInt()
                if (map[i][j] > 0) set.add(Point(i, j))
            }
        }
    }
}

fun solve() {
    var year = 1

    while (true) {
        run()

        if (set.size == 0) {
            year = 0
            break
        }
        if (isFinish()) break

        year++
    }
    println(year)
}

fun run() {
    val targets = hashSetOf<Point>()
    set.forEach { point ->
        var count = 0
        for (i in 0 until 4) {
            val nr = point.row + dir[i][0]
            val nc = point.col + dir[i][1]

            if (nr < 1 || nr > n || nc < 1 || nc > m) continue
            if (map[nr][nc] == 0) count++
        }
        if (map[point.row][point.col] <= count) {
            targets.add(point)
        }
        else map[point.row][point.col] -= count
    }

    targets.forEach { target ->
        map[target.row][target.col] = 0
        set.remove(target)
    }
}

fun isFinish(): Boolean {
    val visit = Array(n + 1) { BooleanArray(m + 1) }
    val que = LinkedList<Int>()

    with(set.first()) {
        que.add(row)
        que.add(col)
        visit[row][col] = true
    }

    var count = 1
    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        for (i in 0 until 4) {
            val nr = row + dir[i][0]
            val nc = col + dir[i][1]

            if (nr < 1 || nr > n || nc < 1 || nc > m) continue
            if (visit[nr][nc] || map[nr][nc] == 0) continue

            que.add(nr)
            que.add(nc)
            visit[nr][nc] = true
            count++
        }
    }

    return count != set.size
}

fun main() {
    input()
    solve()
}