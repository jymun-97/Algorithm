import java.io.*
import java.util.*
import kotlin.math.max
import kotlin.math.min

var n = 0
var m = 0
var sum = 0

lateinit var memory : IntArray
lateinit var cost : IntArray
lateinit var dp : Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    memory = IntArray(n + 1)
    cost = IntArray(n + 1)

    StringTokenizer(readLine()).apply {
        for (i in 1 .. n) memory[i] = nextToken().toInt()
    }
    StringTokenizer(readLine()).apply {
        for (i in 1 .. n) {
            cost[i] = nextToken().toInt()
            sum += cost[i]
        }
    }
    dp = Array(n + 1) { IntArray(sum + 1) }
}

fun solve() {
    var ans = Int.MAX_VALUE

    for (i in 1 .. n) {
        for (j in 0 .. sum) {
            if (j < cost[i]) dp[i][j] = dp[i - 1][j]
            else dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - cost[i]] + memory[i])

            if (dp[i][j] >= m) ans = min(ans, j)
        }
    }
    println(ans)
}

fun main() {
    input()
    solve()
}