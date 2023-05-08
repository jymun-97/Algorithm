
var n = 0
var m = 0
var ans = Int.MAX_VALUE
lateinit var info: Array<IntArray>
lateinit var dp: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    info = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    dp = Array(n) { IntArray(m) { Int.MAX_VALUE } }
}

fun solve() {
    for (i in 0 until m) {
        dp[0][i] = info[0][i]
    }
    for (i in 1 until n) {
        for (j in 0 until m) {
            var min = Int.MAX_VALUE
            for (k in 0 until m) {
                if (j == k) continue
                min = minOf(min, dp[i - 1][k])
            }
            dp[i][j] = info[i][j] + min
        }
    }

    println(dp.last().minOf { it })
}

fun main() {
    input()
    solve()
}