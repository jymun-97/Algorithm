import java.util.*

var a: Int = 0
var b: Int = 0
var c: Int = 0
val dp = hashMapOf<Int, Int>()

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        a = nextToken().toInt()
        b = nextToken().toInt()
        c = nextToken().toInt()
    }
}

fun solve() {
    println(mod(a, b, c))
}

fun mod(a: Int, b: Int, c: Int): Int {
    dp[b]?.let { return it }
    dp[b] = when (b) {
        1 -> a % c
        else -> (mod(a, b / 2, c).toLong() * mod(a, if (b % 2 == 0) b / 2 else b / 2 + 1, c) % c).toInt()
    }
    return dp[b]!!
}

fun main() {
    input()
    solve()
}