import java.util.*
import kotlin.math.max

var n = 0
var m = 0
lateinit var board: Array<Array<String>>
lateinit var apple: Array<IntArray>
lateinit var banana: Array<IntArray>
lateinit var sum: Array<IntArray>
lateinit var dp: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    board = Array(n + 1) { Array(m + 1) { "" } }
    apple = Array(n + 1) { IntArray(m + 1) }
    banana = Array(n + 1) { IntArray(m + 1) }
    dp = Array(n) { IntArray(m) }

    for (i in 1 .. n) {
        board[i] = arrayOf("").plus(readLine().split(" "))
    }
    for (i in n downTo 1) {
        for (j in 1 .. m) {
            val pre = if (board[i][j].isEmpty() || board[i][j][0] != 'A') 0 else board[i][j].substring(1).toInt()
            apple[i - 1][j] = apple[i][j] + pre
        }
    }
    for (i in 0 until n) {
        for (j in 2 .. m) {
            val pre = if (board[i][j].isEmpty() || board[i][j][0] != 'B') 0 else board[i][j].substring(1).toInt()
            banana[i + 1][j] = banana[i][j] + pre
        }
    }
    sum = Array(n) { row -> IntArray(m) { col -> apple[row + 1][col + 1] + banana[row + 1][col + 1] } }
}

fun solve() {
    sum.forEachIndexed { i, v -> dp[i][0] = v[0] }

    for (i in 0 until n) {
        for (j in 1 until m) {
            if (i == 0) {
                dp[i][j] = dp[i][j - 1] + sum[i][j]
                continue
            }
            dp[i][j] = max(dp[i][j - 1] + sum[i][j], dp[i - 1][j - 1] + sum[i][j])
            dp[i][j] = max(dp[i][j], dp[i - 1][j] - apple[i][j])
        }
    }

    dp.forEach { println(it.contentToString()) }
    println(dp.last().last())
}

fun main() {
    input()
    solve()
}