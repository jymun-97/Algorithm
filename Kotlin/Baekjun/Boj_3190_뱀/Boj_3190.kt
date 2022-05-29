import java.util.*
import kotlin.collections.ArrayList

data class Point(
    val row: Int,
    val col: Int
)

const val DOWN = 0
const val UP = 1
const val RIGHT = 2
const val LEFT = 3

var n = 0
val snake = LinkedList<Point>()
val apples = hashSetOf<Point>()
val commands = LinkedList<Pair<Int, String>>()

val direction = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()

    repeat(readLine().toInt()) {
        StringTokenizer(readLine()).apply {
            apples.add(Point(nextToken().toInt(), nextToken().toInt()))
        }
    }
    repeat(readLine().toInt()) {
        StringTokenizer(readLine()).apply {
            commands.add(Pair(nextToken().toInt(), nextToken()))
        }
    }
}

fun solve() {
    snake.add(Point(1, 1))
    var time = 1
    var dir = RIGHT

    run {
        while (true) {
            if (commands.isNotEmpty() && time > commands.peek().first) {
                dir = turn(dir, commands.poll().second)
            }

            val head = snake.first
            val next = Point(
                head.row + direction[dir][0],
                head.col + direction[dir][1]
            )

            if (!inRange(next) || snake.contains(next)) return@run
            snake.addFirst(next)

            if (apples.contains(next)) apples.remove(next)
            else snake.pollLast()

            time++
        }
    }

    println(time)
}

fun turn(dir: Int, str: String) = when (dir) {
    UP -> if (str == "L") LEFT else RIGHT

    DOWN -> if (str == "L") RIGHT else LEFT

    RIGHT -> if (str == "L") UP else DOWN

    else -> if (str == "L") DOWN else UP
}


fun inRange(point: Point) = point.row in 1..n && point.col in 1..n

fun main() {
    input()
    solve()
}