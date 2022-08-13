import java.util.*
import kotlin.math.min

const val size = 10
lateinit var board: Array<Array<Int>>
val count = hashMapOf(
    1 to 5,
    2 to 5,
    3 to 5,
    4 to 5,
    5 to 5
)
var ans = Int.MAX_VALUE
var flag = false

fun input() = with(System.`in`.bufferedReader()) {
    board = Array(size) { readLine().split(" ").map { it.toInt() }.toTypedArray() }
}

fun solve() {
    dfs(0, 0, 0)
    println(if (flag) ans else -1)
}

fun dfs(row: Int, col: Int, cnt: Int) {
    if (row == size) {
        if (check()) {
            ans = min(ans, cnt)
            flag = true
        }
        return
    }
    val next = getNext(row, col)
    when (board[row][col]) {
        0 -> dfs(next.first, next.second, cnt)

        1 -> {
            for (i in 1 .. 5) {
                if (count[i]!! <= 0 || !isValid(row, col, i)) continue

                convert(row, col, i, 0)
                count[i] = count[i]!! - 1

                dfs(next.first, next.second, cnt + 1)

                count[i] = count[i]!! + 1
                convert(row, col, i, 1)
            }
        }
    }
}

fun print() {
    board.forEach { println(it.contentToString()) }
    println()
}

fun isValid(row: Int, col: Int, k: Int) : Boolean {
    if (row + k > size || col + k > size) return false
    for (i in 0 until k) {
        for (j in 0 until k) {
            if (board[row + i][col + j] == 0) return false
        }
    }
    return true
}

fun convert(row: Int, col: Int, k: Int, to: Int) {
    for (i in 0 until k) {
        for (j in 0 until k) {
            board[row + i][col + j] = to
        }
    }
}

fun getNext(row: Int, col: Int) = when (col) {
    size - 1 -> row + 1 to 0
    else -> row to col + 1
}

fun check() : Boolean {
    board.forEach {
        if (it.contains(1)) return false
    }
    return true
}

fun main() {
    input()
    solve()
}