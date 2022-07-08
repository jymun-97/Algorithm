import java.util.*
import kotlin.math.abs

data class Info(
    val red: Point,
    val blue: Point,
    val count: Int,
    val route: StringBuilder,
    val direction: Int
)
data class Point(
    val row: Int,
    val col: Int
)
var n = 0
var m = 0
lateinit var map : Array<StringBuilder>
lateinit var sr : Point
lateinit var sb : Point
lateinit var hole : Point

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)
val dirChar = charArrayOf('D', 'U', 'R', 'L')

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    map = Array(n) { StringBuilder(readLine()) }
}

fun solve() {
    init()

    val que = LinkedList<Info>()
    que.add(Info(sr, sb, 0, StringBuilder(), -1))

    while (que.isNotEmpty()) {
        val info = que.poll()

        if (info.count > 10 || info.blue == hole) continue
        if (info.red == hole) {
            println(StringBuilder().append(info.count).append('\n').append(info.route))
            return
        }

        for (i in 0 until 4) {
            if (i == info.direction) continue

            var red = move(info.red, info.blue, i)
            var blue = move(info.blue, red, i)
            red = move(red, blue, i)
            blue = move(blue, red, i)

            que.add(
                Info(
                    red = red,
                    blue = blue,
                    count = info.count + 1,
                    route = StringBuilder(info.route).append(dirChar[i]),
                    direction = i
                )
            )
        }
    }

    println(-1)
}

fun move(point: Point, other: Point, d: Int) : Point {
    if (point == hole) return point

    var row = point.row
    var col = point.col

    while (map[row][col] != '#') {
        row += dir[d][0]
        col += dir[d][1]

        val p = Point(row, col)
        if (p == hole) return p
        if (p == other) break
    }

    return Point(row - dir[d][0], col - dir[d][1])
}

fun init() {
    for (i in 0 until n) {
        for (j in 0 until m) {
            when (map[i][j]) {
                'R' -> sr = Point(i, j)

                'B' -> sb = Point(i, j)

                'O' -> hole = Point(i, j)
            }
        }
    }
}

fun main() {
    input()
    solve()
}