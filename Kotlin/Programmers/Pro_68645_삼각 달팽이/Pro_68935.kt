import java.util.LinkedList

enum class Direction(
    val dr: Int,
    val dc: Int,
) {
    DOWN(1, 0),
    RIGHT(0, 1),
    LEFT_UP(-1, -1);

    fun next() = when (this) {
        DOWN -> RIGHT

        RIGHT -> LEFT_UP

        else -> DOWN
    }
}

class Solution {
    var n = 0
    lateinit var board: Array<IntArray>

    fun solution(n: Int): IntArray {
        this.n = n
        board = Array(n) { IntArray(n) }

        fillNums()

        return board.fold(LinkedList<Int>()) { acc, arr ->
            acc.addAll(arr.filter { it != 0 })
            acc
        }.toIntArray()
    }

    fun fillNums() {
        var row = 0
        var col = 0
        var dir = Direction.DOWN
        var num = 1

        while (num <= (n * n + n) / 2) {
            board[row][col] = num++

            val nr = row + dir.dr
            val nc = col + dir.dc

            if (nr !in 0 until n || nc !in 0 until n || board[nr][nc] != 0) {
                dir = dir.next()
                row += dir.dr
                col += dir.dc
            }
            else {
                row = nr
                col = nc
            }
        }
    }
}