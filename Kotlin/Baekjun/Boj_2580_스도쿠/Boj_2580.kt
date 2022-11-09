import java.util.*
import kotlin.collections.HashSet

data class Number(
    val value: Int,
    val row: Int,
    val col: Int
) {
    val group: Int
        get() = row / 3 * 3 + col / 3
}
val nums = hashSetOf<Number>()
val empty = hashSetOf<Number>()
lateinit var board: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    board = Array(9) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    for (row in 0 until 9) {
        for (col in 0 until 9) {
            if (board[row][col] == 0) empty.add(Number(board[row][col], row, col))
            else nums.add(Number(board[row][col], row, col))
        }
    }
}

fun solve() {
    while (empty.isNotEmpty()) {
        val temp = HashSet(empty)
        temp.forEach {
            fill(it)
        }
    }
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

fun fill(target: Number) {
    checkRow(target.row)?.let {
        addNumber(target, it)
        return
    }
    checkCol(target.col)?.let {
        addNumber(target, it)
        return
    }
    checkGroup(target.group)?.let {
        addNumber(target, it)
    }
}

fun addNumber(target: Number, value: Int) {
    empty.remove(target)
    nums.add(Number(value, target.row, target.col))
    board[target.row][target.col] = value
}

fun checkRow(row: Int): Int? {
    val filtered = nums.filter { it.row == row }.map { it.value }.toHashSet()
    return if (filtered.size < 8) null else {
        (1..9).find {
            !filtered.contains(it)
        }
    }
}

fun checkCol(col: Int): Int? {
    val filtered = nums.filter { it.col == col }.map { it.value }.toHashSet()
    return if (filtered.size < 8) null else {
        (1..9).find {
            !filtered.contains(it)
        }
    }
}

fun checkGroup(group: Int): Int? {
    val filtered = nums.filter { it.group == group }.map { it.value }.toHashSet()
    return if (filtered.size < 8) null else {
        (1..9).find {
            !filtered.contains(it)
        }
    }
}

fun main() {
    input()
    solve()
}