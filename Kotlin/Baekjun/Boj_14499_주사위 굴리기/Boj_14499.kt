import java.util.*

const val EAST = 1
const val WEST = 2
const val NORTH = 3
const val SOUTH = 4

data class Point(
    val row: Int,
    val col: Int
)
class Cube(location: Point) {
    var loc = location
    var up = 0
    var down = 0
    var left = 0
    var right = 0
    var front = 0
    var back = 0

    fun roll(dir: Int, value: Int) {
        val temp = down
        when (dir) {
            EAST -> {
                down = right
                right = up
                up = left
                left = temp
            }
            WEST -> {
                down = left
                left = up
                up = right
                right = temp
            }
            NORTH -> {
                down = back
                back = up
                up = front
                front = temp
            }
            SOUTH -> {
                down = front
                front = up
                up = back
                back = temp
            }
        }
        if (value != 0) down = value
    }
}

var n = 0
var m = 0
var k = 0
lateinit var map : Array<IntArray>
lateinit var cube: Cube
lateinit var commands: IntArray

val dir = arrayOf(
    arrayOf(0, 1),
    arrayOf(0, -1),
    arrayOf(-1, 0),
    arrayOf(1, 0),
)

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        cube = Cube(Point(nextToken().toInt(), nextToken().toInt()))
        k = nextToken().toInt()
    }
    map = Array(n) { readLine().split(' ').map { it.toInt() }.toIntArray() }
    commands = readLine().split(' ').map { it.toInt() }.toIntArray()
}

fun solve() {
    val ans = StringBuilder()
    commands.forEach {
        val loc = cube.loc

        val nr = loc.row + dir[it - 1][0]
        val nc = loc.col + dir[it - 1][1]

        if (nr in 0 until n && nc in 0 until m) {
            cube.loc = Point(nr, nc)
            cube.roll(it, map[nr][nc])
            if (map[nr][nc] == 0) map[nr][nc] = cube.down
            else map[nr][nc] = 0

            ans.append(cube.up).append('\n')
        }
    }

    println(ans)
}

fun main() {
    input()
    solve()
}