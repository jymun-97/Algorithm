import java.util.LinkedList

data class Point(
    val row: Int,
    val col: Int
) {
    fun isInRange(maxRow: Int, maxCol: Int) = row in 0 until maxRow && col in 0 until maxCol
}
class Solution {
    var n = 0
    var m = 0
    lateinit var start: Point
    lateinit var lever: Point
    lateinit var end: Point
    lateinit var board: List<CharArray>

    val dir = arrayOf(
        arrayOf(1, 0),
        arrayOf(-1, 0),
        arrayOf(0, 1),
        arrayOf(0, -1)
    )

    fun solution(maps: Array<String>): Int {
        init(maps)

        val startToLever = bfs(start, lever) ?: return -1
        val leverToEnd = bfs(lever, end) ?: return -1

        return startToLever + leverToEnd
    }

    fun init(maps: Array<String>) {
        board = maps.map { it.toCharArray() }
        n = board.size
        m = board[0].size

        for (i in 0 until n) {
            for (j in 0 until m) {
                when (board[i][j]) {
                    START -> start = Point(i, j)

                    LEVER -> lever = Point(i, j)

                    END -> end = Point(i, j)
                }
            }
        }
    }

    fun bfs(s: Point, e: Point): Int? {
        val visit = hashSetOf<Point>()
        val que = LinkedList<Pair<Point, Int>>()
        que.add(s to 0)
        visit.add(s)

        while (que.isNotEmpty()) {
            val (from, dist) = que.poll()

            if (from == e) return dist

            dir.forEach {
                val to = Point(from.row + it[0], from.col + it[1])

                if (!visit.contains(to) && to.isInRange(n, m) && board[to] != WALL) {
                    que.add(to to dist + 1)
                    visit.add(to)
                }
            }
        }

        return null
    }

    companion object {
        const val START = 'S'
        const val END = 'E'
        const val LEVER = 'L'
        const val EMPTY = 'O'
        const val WALL = 'X'
    }
}

operator fun List<CharArray>.get(point: Point) = this[point.row][point.col]