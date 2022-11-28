import java.util.*
import kotlin.math.max

var n = 0
lateinit var board: List<List<Int>>
lateinit var dp: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = List(n) { readLine().split(" ").map { it.toInt() } }
    dp = Array(n) { IntArray(n) }
}

fun solve() {
    var ans = 0
    val que = LinkedList<Int>().apply {
        add(0)
        add(0)
        add(board[0][0])
        dp[0][0] = board[0][0]
    }
    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        val sum = que.poll()

        ans = max(ans, sum)
        if (row == n - 1 || sum < dp[row][col]) continue

        repeat(2) {
            val next = sum + board[row + 1][col + it]
            if (dp[row + 1][col + it] < next) que.apply {
                add(row + 1)
                add(col + it)
                add(next)
                dp[row + 1][col + it] = next
            }
        }
    }

    println(ans)
}

fun main() {
    input()
    solve()
}