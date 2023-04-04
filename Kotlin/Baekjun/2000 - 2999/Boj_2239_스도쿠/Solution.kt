var count = 0
var found = false
lateinit var board: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    board = Array(9) { readLine().map { it.digitToInt() }.toIntArray() }
    count = board.sumOf { it.count { it == 0 } }
}

fun solve() {
    dfs(0, 0, count)
    print(
        buildString {
            board.forEach {
                appendLine(it.joinToString(""))
            }
        }
    )
}

fun dfs(row: Int, col: Int, remain: Int): Unit = with(getNextPoint(row, col)) {
    when {
        row == 9 -> found = remain == 0

        board[row][col] != 0 -> dfs(first, second, remain)

        else -> for (value in 1..9) {
            if (isValid(row, col, value)) {
                board[row][col] = value

                dfs(first, second, remain - 1)
                if (found) break

                board[row][col] = 0
            }
        }
    }
}

fun getNextPoint(row: Int, col: Int): Pair<Int, Int> {
    val nr = if (col == 8) row + 1 else row
    val nc = if (col == 8) 0 else col + 1

    return nr to nc
}

fun isValid(row: Int, col: Int, target: Int): Boolean =
    isValidInGroup(row, col, target) && isValidInRow(row, target) && isValidInCol(col, target)

fun isValidInGroup(row: Int, col: Int, target: Int): Boolean {
    val sr = row / 3 * 3
    val sc = col / 3 * 3

    for (i in sr until sr + 3) {
        for (j in sc until sc + 3) {
            if (board[i][j] == target) return false
        }
    }

    return true
}

fun isValidInRow(row: Int, target: Int): Boolean = board[row].none { it == target }

fun isValidInCol(col: Int, target: Int): Boolean = board.none { it[col] == target }


fun main() {
    input()
    solve()
}