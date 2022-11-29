import java.util.*

var n = 0
var r = 0
var c = 0
lateinit var dp: IntArray
lateinit var pow: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        r = nextToken().toInt()
        c = nextToken().toInt()
    }
    dp = IntArray(n + 1)
    pow = IntArray(n + 1)
}

fun init() {
    dp[0] = 1
    pow[0] = 1

    for (i in 1..n) {
        dp[i] = dp[i - 1] * 4
        pow[i] = pow[i - 1] * 2
    }
}

fun solve() {
    init()

    val index = (pow.indexOfLast { r >= it } + 1).coerceAtLeast(pow.indexOfLast { c >= it } + 1)
    println(dfs(index, r, c, 0))
}

fun dfs(k: Int, row: Int, col: Int, sum: Int): Int {
    if (k == 0) return sum

    val nr = if (row < pow[k - 1]) row else row - pow[k - 1]
    val nc = if (col < pow[k - 1]) col else col - pow[k - 1]
    val quadrant = when {
        row / pow[k - 1] == 0 && col / pow[k - 1] == 0 -> 0

        row / pow[k - 1] == 0 && col / pow[k - 1] == 1 -> 1

        row / pow[k - 1] == 1 && col / pow[k - 1] == 0 -> 2

        else -> 3
    }
    return dfs(k - 1, nr, nc, sum + dp[k - 1] * quadrant)
}

fun main() {
    input()
    solve()
}