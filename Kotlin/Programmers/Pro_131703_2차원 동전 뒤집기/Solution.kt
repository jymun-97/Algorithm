
class Solution {
    var n = 0
    var m = 0
    var ans = Int.MAX_VALUE
    lateinit var board: Array<IntArray>

    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        n = beginning.size
        m = beginning[0].size

        board = Array(n) { IntArray(m) }

        for (i in 0 until n) {
            for (j in 0 until m) {
                board[i][j] = if (beginning[i][j] == target[i][j]) 1 else 0
            }
        }

        dfs(0, 0, 0)

        return if (ans == Int.MAX_VALUE) -1 else ans
    }

    fun dfs(k: Int, count: Int, type: Int): Unit = when {
        count >= ans -> {}

        isMatch() -> ans = count

        type == 0 && k == n -> dfs(0, count, 1)

        type == 1 && k == m -> {}

        type == 0 -> {
            dfs(k + 1, count, 0)
            reverseRow(k)
            dfs(k + 1, count + 1, 0)
            reverseRow(k)
        }

        else -> {
            dfs(k + 1, count, 1)
            reverseCol(k)
            dfs(k + 1, count + 1, 1)
            reverseCol(k)
        }
    }

    fun isMatch() = board.sumOf { it.sum() } == n * m

    fun reverseRow(row: Int) {
        for (col in 0 until m) {
            board[row][col] = 1 - board[row][col]
        }
    }

    fun reverseCol(col: Int) {
        for (row in 0 until n) {
            board[row][col] = 1 - board[row][col]
        }
    }
}