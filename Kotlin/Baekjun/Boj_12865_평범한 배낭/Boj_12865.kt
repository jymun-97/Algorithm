import java.util.*
import kotlin.math.max

data class Info(
    val weight: Int,
    val value: Int
)
var n = 0
var ans = 0
var limit = 0
lateinit var arr: List<Info>
lateinit var dp: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        limit = nextToken().toInt()
    }
    dp = IntArray(limit + 1)
    arr = List(n) {
        val (weight, value) = readLine().split(" ").map { it.toInt() }
        Info(weight, value)
    }.sortedWith(compareBy( { it.weight }, { -it.value }))
}

fun solve() {
    for (i in 0 until arr[0].weight) dp[i]
}

fun main() {
    input()
    solve()
}