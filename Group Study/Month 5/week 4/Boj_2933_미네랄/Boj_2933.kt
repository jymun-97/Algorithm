import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

data class Point(
    val row: Int,
    val col: Int
)
var r = 0
var c = 0
var n = 0
lateinit var map: Array<CharArray>
lateinit var heights: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        r = nextToken().toInt()
        c = nextToken().toInt()
    }
    map = Array(r + 1) { CharArray(c + 1) }

    for (i in r downTo 1) {
        val line = readLine()
        for (j in 1..c) {
            map[i][j] = line[j - 1]
        }
    }

    n = readLine().toInt()
    heights = IntArray(n)
    StringTokenizer(readLine()).apply {
        for (i in 0 until n)
            heights[i] = nextToken().toInt()
    }
}

fun solve() {
    var flag = false
    heights.forEach { h ->
        var target = if (!flag) 1 else c
        while (target in 1..c && map[h][target] != 'x')
            if (!flag) target++
            else target--

        if (target in 1..c) {
            map[h][target] = '.'

            val floatings = getFloatings()
            drop(floatings)
        }
        flag = !flag
    }

    printMap()
}

fun drop(floatings: ArrayList<Point>) {
    val heights = IntArray(c + 1)
    for (col in 1..c) {
        var height = 0
        for (row in r downTo 1) {
            if (map[row][col] == 'x') {
                height = row
                break
            }
        }
        heights[col] = height
    }

    var size = r
    floatings.forEach { point ->
        size =
            if (point.row < heights[point.col]) min(size, point.row - 1)
            else min(point.row - heights[point.col] - 1, size)
    }

    floatings.forEach { point ->
        map[point.row - size][point.col] = 'x'
    }
}

fun getFloatings(): ArrayList<Point> {
    val dir = arrayOf(
        arrayOf(1, 0),
        arrayOf(-1, 0),
        arrayOf(0, 1),
        arrayOf(0, -1)
    )
    val visit = Array(r + 1) { BooleanArray(c + 1) }
    val que = LinkedList<Int>()
    for (col in 1..c) {
        if (map[1][col] == 'x') {
            que.add(1)
            que.add(col)
            visit[1][col] = true
        }
    }

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        for (i in 0 until 4) {
            val nr = row + dir[i][0]
            val nc = col + dir[i][1]

            if (nr < 1 || nr > r || nc < 1 || nc > c) continue
            if (visit[nr][nc] || map[nr][nc] == '.') continue

            que.add(nr)
            que.add(nc)
            visit[nr][nc] = true
        }
    }

    val list = arrayListOf<Point>()
    for (i in 1..r) {
        for (j in 1..c) {
            if (map[i][j] == 'x' && !visit[i][j]) {
                list.add(Point(i, j))
                map[i][j] = '.'
            }
        }
    }
    return list
}


fun printMap() {
    for (row in r downTo 1) {
        for (col in 1..c) {
            print("${map[row][col]}")
        }
        println()
    }
}

fun main() {
    input()
    solve()
}