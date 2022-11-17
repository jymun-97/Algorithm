import java.util.*
import kotlin.math.max

var n = 0
var k = 0
var ans = 0
lateinit var weights: IntArray
lateinit var values: IntArray
lateinit var dp: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        k = nextToken().toInt()
    }
    weights = IntArray(n)
    values = IntArray(n)
    repeat(4) {
        val (weight, value) = readLine().split(" ").map { it.toInt() }
        weights[it] = weight
        values[it] = value
    }
    dp = Array(n) { IntArray(k + 1) { -1 } }
}

fun solve() {
    println(knapsack(n - 1, k))
}

fun knapsack(i: Int, weight: Int): Int {
    if (weight <= 0 || i < 0) return 0
    if (dp[i][weight] != -1) return dp[i][weight]

    dp[i][weight] = if (weights[i] <= weight) max(
        knapsack(i - 1, weight - weights[i]) + values[i],
        knapsack(i - 1, weight)
    ) else knapsack(i - 1, weight)
    return dp[i][weight]
}

fun main() {
    input()
    solve()
}