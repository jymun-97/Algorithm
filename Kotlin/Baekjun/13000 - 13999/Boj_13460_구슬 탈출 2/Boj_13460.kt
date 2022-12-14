import java.util.*

data class Point(
    var row: Int,
    var col: Int
)

var n = 0
var m = 0

lateinit var map: Array<StringBuilder>
lateinit var R: Point
lateinit var B: Point
lateinit var target: Point

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
    map = Array(n) { StringBuilder(readLine()) }

    for (i in 0 until n) {
        for (j in 0 until m) {
            when (map[i][j]) {
                'R' -> R = Point(i, j)

                'B' -> B = Point(i, j)

                'O' -> target = Point(i, j)
            }
        }
    }
    map[R.row][R.col] = '.'
    map[B.row][B.col] = '.'
}

fun solve() {
    println(bfs())
}

fun bfs() : Int {
    val dist = HashMap<Pair<Point, Point>, Int>()

    val que = LinkedList<Point>()
    que.add(R)
    que.add(B)
    dist[Pair(R, B)] = 0

    while (que.isNotEmpty()) {
        val red = que.poll()
        val blue = que.poll()
        val now = Pair(red, blue)

        if (dist[Pair(red, blue)]!! > 10 || blue == target) continue
        if (red == target) return dist[now]!!

        for (i in 0 until 4) {
            var nextRed = move(red, blue, i)
            var nextBlue = move(blue, red, i)
            nextRed = move(nextRed, nextBlue, i)
            nextBlue = move(nextBlue, nextRed, i)
            val next = Pair(nextRed, nextBlue)

            if (dist.containsKey(next)) continue

            que.add(nextRed)
            que.add(nextBlue)
            dist[next] = dist[now]!! + 1
        }
    }

    return -1
}

fun move(p: Point, other: Point, d: Int): Point {
    val point = Point(p.row, p.col)
    while (map[point.row][point.col] == '.' && (point != other || other == target)) {
        point.row += dir[d][0]
        point.col += dir[d][1]
    }

    if (point != target) {
        point.row -= dir[d][0]
        point.col -= dir[d][1]
    }

    return point
}

fun main() {
    input()
    solve()
}