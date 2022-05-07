import java.util.*
import kotlin.collections.HashSet

const val EMPTY = 0
const val SNAKE = 1
const val APPLE = 2

data class Point(
    val row: Int,
    val col: Int
)

var n = 0
lateinit var turn: LinkedList<Pair<Int, String>>
lateinit var map: Array<IntArray>
lateinit var head: Point
lateinit var tail: Point

val direction = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    turn = LinkedList()

    n = readLine().toInt()
    map = Array(n + 1) { IntArray(n + 1) { EMPTY } }

    repeat(readLine().toInt()) {
        StringTokenizer(readLine()).apply {
            map[nextToken().toInt()][nextToken().toInt()] = APPLE
        }
    }
    repeat(readLine().toInt()) {
        StringTokenizer(readLine()).apply {
            turn.add(Pair(nextToken().toInt(), nextToken()))
        }
    }
}

fun solve() {
    map[1][1] = SNAKE
    var time = 0
    var dir = 2
    head = Point(1, 1)
    tail = Point(1, 1)

    loop@
    while (turn.isNotEmpty()) {
        val command = turn.poll()

        while (time <= command.first) {
            val nr = head.row + direction[dir][0]
            val nc = head.col + direction[dir][1]

            if (!inRange(nr, nc) && map[nr][nc] == SNAKE) break@loop

            when (map[nr][nc]) {
                EMPTY -> {
                    map[nr][nc] = SNAKE
                    map[tail.row][tail.col] = EMPTY
                }
                APPLE -> {
                    map[nr][nc] = SNAKE

                }
            }
            time++
        }
    }

    println(time)
}

fun updateTail() {
    val row = tail.row
    val col = tail.col

    for (i in 0 until 4) {
        val nr = row + dir[i][0]
        val nc =
    }
}

fun inRange(row: Int, col: Int) = row in 1 .. n && col in 1 .. n

fun main() {
    input()
    solve()
}