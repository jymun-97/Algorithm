class Solution {
    lateinit var board: Array<String>

    fun solution(board: Array<String>): Int {
        this.board = board

        val countDiff = countOf(FIRST) - countOf(LAST)
        val resultOfFirst = isComplete(FIRST)
        val resultOfLast = isComplete(LAST)

        return when {
            countDiff !in 0..1 -> 0

            resultOfLast -> if (resultOfFirst || countDiff == 1) 0 else 1

            resultOfFirst && countDiff == 0 -> 0

            else -> 1
        }
    }

    fun countOf(target: Char) = board.sumOf { it.count { it == target } }

    fun isComplete(target: Char): Boolean = checkRow(target) || checkCol(target) || checkDiagonal(target)

    fun checkRow(target: Char) = board.contains("$target$target$target")

    fun checkCol(target: Char) = (0 until 3).any { col -> board.all { it[col] == target } }

    fun checkDiagonal(target: Char) = (board[0][0] == target && board[1][1] == target && board[2][2] == target) ||
            (board[0][2] == target && board[1][1] == target && board[2][0] == target)

    companion object {
        const val FIRST = 'O'
        const val LAST = 'X'
    }
}
