import java.util.LinkedList

data class Point(
    val row: Int,
    val col: Int
) {
    fun isInRange(max: Int) = row in 0 until max && col in 0 until max

    fun moved(dir: IntArray) = Point(row + dir[0], col + dir[1])
}

data class Robot(
    val p1: Point,
    val p2: Point
) {
    val dir = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(-1, 0),
        intArrayOf(0, 1),
        intArrayOf(0, -1)
    )

    fun isInRange(max: Int) = p1.isInRange(max) && p2.isInRange(max)

    fun contains(point: Point) = p1 == point || p2 == point

    fun moved() = dir.map { Robot(p1.moved(it), p2.moved(it)) }

    fun rotated() = listOf(
        if (p1.row == p2.row) Robot(p1, Point(p2.row + 1, p1.col)) to Point(p2.row + 1, p2.col) else Robot(p1, Point(p1.row, p2.col + 1)) to Point(p2.row, p2.col + 1),
        if (p1.row == p2.row) Robot(p1, Point(p2.row - 1, p1.col)) to Point(p2.row - 1, p2.col) else Robot(p1, Point(p1.row, p2.col - 1)) to Point(p2.row, p2.col - 1),
        if (p1.row == p2.row) Robot(Point(p1.row + 1, p2.col), p2) to Point(p1.row + 1, p1.col) else Robot(Point(p2.row, p1.col + 1), p2) to Point(p1.row, p1.col + 1),
        if (p1.row == p2.row) Robot(Point(p1.row - 1, p2.col), p2) to Point(p1.row - 1, p1.col) else Robot(Point(p2.row, p1.col - 1), p2) to Point(p1.row, p1.col - 1),
    )

    override fun equals(o: Any?): Boolean {
        val other = o as Robot
        return (p1 == other.p1 && p2 == other.p2) || (p1 == other.p2 && p2 == other.p1)
    }

    override fun hashCode(): Int = p1.hashCode() + p2.hashCode()
}

class Solution {
    var n = 0
    val visit = hashSetOf<Robot>()
    lateinit var dest: Point
    lateinit var start: Robot
    lateinit var board: Array<IntArray>

    fun solution(board: Array<IntArray>): Int {
        init(board)
        return bfs(start)
    }

    fun init(board: Array<IntArray>) {
        this.board = board
        n = board.size
        dest = Point(n - 1, n - 1)
        start = Robot(Point(0, 0), Point(0, 1))
    }

    fun bfs(start: Robot): Int {
        var ans = 0
        val que = LinkedList<Pair<Robot, Int>>()
        que.add(start to 0)
        visit.add(start)

        while (que.isNotEmpty()) {
            val (from, dist) = que.poll()

            if (from.contains(dest)) {
                ans = dist
                break
            }

            from.moved().forEach { to ->
                if (!to.isInRange(n) || visit.contains(to)) return@forEach
                if (board[to.p1] == 1 || board[to.p2] == 1) return@forEach

                que.add(to to dist + 1)
                visit.add(to)
            }
            from.rotated().forEach {
                val (to, passed) = it

                if (!to.isInRange(n) || visit.contains(to)) return@forEach
                if (board[passed] == 1 || board[to.p1] == 1 || board[to.p2] == 1) return@forEach

                que.add(to to dist + 1)
                visit.add(to)
            }
        }

        return ans
    }
}

operator fun Array<IntArray>.get(point: Point) = this[point.row][point.col]