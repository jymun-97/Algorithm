import java.io.*
import java.util.*
import kotlin.collections.HashMap

data class Point(
    val row: Int,
    val col: Int
)

var n = 0
var m = 0
var limit = 0

lateinit var start: Point
lateinit var map: Array<IntArray>
lateinit var dest: HashMap<Point, Point>

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
        limit = nextToken().toInt()
    }

    dest = hashMapOf()
    map = Array(n + 1) { IntArray(n + 1) }
    for (i in 1..n) {
        StringTokenizer(readLine()).apply {
            for (j in 1..n) {
                map[i][j] = nextToken().toInt()
            }
        }
    }

    StringTokenizer(readLine()).apply {
        start = Point(
            row = nextToken().toInt(),
            col = nextToken().toInt()
        )
    }

    for (i in 1..m) {
        StringTokenizer(readLine()).apply {
            dest[Point(
                row = nextToken().toInt(),
                col = nextToken().toInt()
            )] = Point(
                row = nextToken().toInt(),
                col = nextToken().toInt()
            )
        }
    }
}

fun solve() {
    while (m --> 0) {
        for (i in 1..2) {
            if (i == 1 && dest.containsKey(start)) continue
            move().also { cost ->
                limit -= cost
                if (cost == -1 || limit < 0) {
                    println(-1)
                    return
                }
                if (i == 2) limit += cost * 2
            }
        }
    }
    println(limit)
}

fun move() : Int {
    val dist = hashMapOf<Point, Int>()
    val flag = dest.containsKey(start)
    val que =
        if (flag) LinkedList<Point>()
        else PriorityQueue() { p1, p2 ->
            when {
                dist[p1]!! == dist[p2]!! -> {
                    if (p1.row == p2.row) p1.col - p2.col else p1.row - p2.row
                }
                else -> dist[p1]!! - dist[p2]!!
            }
        }

    start.let {
        que.add(it)
        dist[it] = 0
    }

    while (que.isNotEmpty()) {
        val from = que.poll()

        if (flag && dest[start] == from) {
            dest.remove(start)

            start = from
            return dist[from]!!
        }
        if (!flag && dest.containsKey(from)) {
            start = from
            return dist[from]!!
        }

        for (i in 0 until 4) {
            val to = Point(
                from.row + dir[i][0],
                from.col + dir[i][1]
            )
            if (to.row < 1 || to.row > n || to.col < 1 || to.col > n) continue
            if (map[to.row][to.col] == 1) continue
            if (dist.containsKey(to)) continue

            dist[to] = dist[from]!! + 1
            que.add(to)
        }
    }
    return -1
}

fun main() {
    input()
    solve()
}