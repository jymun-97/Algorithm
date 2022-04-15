import java.util.*
import kotlin.math.abs
import kotlin.math.min

var n = 0

lateinit var arr: IntArray
lateinit var dp: Array<Array<IntArray>>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = countTokens() - 1
        arr = IntArray(n)

        for (i in 0 until n) {
            arr[i] = nextToken().toInt()
        }
    }
    dp = Array(n) { Array(5) { IntArray(5) } }
}

fun solve() {
    print(dfs(0, 0, 0))
}

fun dfs(k: Int, left: Int, right: Int): Int {
    if (k == n) return 0
    if (dp[k][left][right] != 0) return dp[k][left][right]

    val next = arr[k]
    val leftResult = dfs(k + 1, next, right) + getCost(left, next)
    val rightResult = dfs(k + 1, left, next) + getCost(right, next)

    dp[k][left][right] = min(leftResult, rightResult)

    return dp[k][left][right]
}

fun getCost(from: Int, to: Int) = when {
    from == 0 || to == 0 -> 2

    from == to -> 1

    abs(from - to) == 2 -> 4

    else -> 3
}

fun main() {
    input()
    solve()
}