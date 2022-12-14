import java.util.*
import kotlin.collections.HashSet

var isFinish = false
lateinit var board: Array<IntArray>

val numsInRow = Array<HashSet<Int>>(9) { hashSetOf() }
val numsInCol = Array<HashSet<Int>>(9) { hashSetOf() }
val numsInGroup = Array<HashSet<Int>>(9) { hashSetOf() }

fun input() = with(System.`in`.bufferedReader()) {
    board = Array(9) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    for (i in 0 until 9) {
        for (j in 0 until 9) {
            if (board[i][j] != 0) {
                val value = board[i][j]
                numsInRow[i].add(value)
                numsInCol[j].add(value)
                numsInGroup[getGroup(i, j)].add(value)
            }
        }
    }
}

fun solve() {
    fill(0, 0)
    print()
}

fun print() {
    for (i in 0 until 9) {
        for (j in 0 until 9) {
            print("${board[i][j]} ")
        }
        println()
    }
}

fun getGroup(row: Int, col: Int) = row / 3 * 3 + col / 3

fun fill(row: Int, col: Int) {
    when {
        row >= 9 -> {
            isFinish = true
            return
        }
        col >= 9 -> {
            fill(row + 1, 0)
            return
        }
        board[row][col] != 0 -> {
            fill(row, col + 1)
            return
        }
    }
    for (value in 1..9) {
        val group = getGroup(row, col)
        if (isValid(row, col, group, value)) {
            addNumber(row, col, group, value)
            fill(row, col + 1)

            if (!isFinish) removeNumber(row, col, group, value)
        }
    }
}

fun isValid(row: Int, col: Int, group: Int, value: Int): Boolean =
    !numsInRow[row].contains(value) && !numsInCol[col].contains(value) && !numsInGroup[group].contains(value)

fun addNumber(row: Int, col: Int, group: Int, value: Int) {
    board[row][col] = value
    numsInRow[row].add(value)
    numsInCol[col].add(value)
    numsInGroup[group].add(value)
}

fun removeNumber(row: Int, col: Int, group: Int, value: Int) {
    board[row][col] = 0
    numsInRow[row].remove(value)
    numsInCol[col].remove(value)
    numsInGroup[group].remove(value)
}

fun main() {
    input()
    solve()
}