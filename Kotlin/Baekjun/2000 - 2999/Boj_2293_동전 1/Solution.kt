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
    make(0, 0)
    println(dp.contentToString())
    println(dp[k])
}

fun make(i: Int, sum: Int) {
    if (i == n || sum > k) return

    dp[sum]++
    for (j in i until n) {
        make(j, sum + coins[j])
    }
}

fun main() {
    input()
    solve()
}