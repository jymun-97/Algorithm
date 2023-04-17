var n = 0
var k = 0
lateinit var coins: List<Int>
lateinit var dp: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        k = this[1]
    }
    dp = IntArray(k + 1)
    coins = List(n) { readLine().toInt() }.sorted()
}

fun solve() {
    dp[0] = 1
    coins.forEach { coin ->
        for (target in coin..k) {
            dp[target] += dp[target - coin]
        }
    }

    println(dp[k])
}

fun main() {
    input()
    solve()
}