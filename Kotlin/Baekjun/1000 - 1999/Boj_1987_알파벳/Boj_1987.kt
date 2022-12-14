import java.util.*
import kotlin.collections.HashSet
import kotlin.math.max

var n = 0
var m = 0
var ans = 1
val visit = hashSetOf<Char>()
lateinit var board: Array<CharArray>

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    board = Array(n) { readLine().toCharArray() }
}

fun solve() {
    dfs(0, 0)
    println(ans)
}

fun dfs(row: Int, col: Int) {
    if (visit.contains(board[row][col])) return

    visit.add(board[row][col])
    ans = max(ans, visit.size)
    dir.forEach {
        val nr = row + it[0]
        val nc = col + it[1]

        if (nr in 0 until n && nc in 0 until m) {
            dfs(nr, nc)
        }
    }
    visit.remove(board[row][col])
}

fun main() {
    input()
    solve()
}