data class Shark(
    val size: Int,
    var dir: Int,
    val speed: Int,
)

var r = 0
var c = 0
var m = 0
var sum = 0
lateinit var board: Array<Array<Shark?>>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        r = this[0]
        c = this[1]
        m = this[2]
    }
    board = Array(r + 1) { Array(c + 1) { null } }

    repeat(m) {
        val (row, col, size, dir, speed) = readLine().split(" ").map { it.toInt() }
        board[row][col] = Shark(size, dir, speed)
    }
}

fun solve() {
    printBoard()
    for (loc in 1..c) {
        catch(loc)
        move()
        printBoard()
    }
    println(sum)
}

fun catch(col: Int) {
    for (row in 1..r) {
        board[row][col]?.let {
            sum += it.size
            board[row][col] = null

            return
        }
    }
}

fun move() {
    val temp = Array(r + 1) { Array<Shark?>(c + 1) { null } }
    for (row in 1..r) {
        for (col in 1..c) {
            board[row][col]?.let { shark ->
                when (shark.dir) {
                    1 -> {
                        val (nr, nd) = if (row - shark.speed > 0) row - shark.speed to shark.dir else {
                            val dist = shark.speed - row + 1
                            dist % row to
                        }
                    }
                    2 -> {

                    }
                    3 -> {

                    }
                    4 -> {

                    }
                }
            }
        }
    }
    board = temp
}

fun printBoard() {
    print(
        buildString {
            for (row in 1..r) {
                for (col in 1..c) {
                    append(board[row][col]?.size ?: 0).append(' ')
                }
                appendLine()
            }
            appendLine()
        }
    )
}

fun main() {
    input()
    solve()
}