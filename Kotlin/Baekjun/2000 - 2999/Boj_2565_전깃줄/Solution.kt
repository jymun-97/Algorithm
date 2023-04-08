
var n = 0
lateinit var lines: IntArray
lateinit var dp: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    lines = List(n) { readLine().split(" ")
        .map { it.toInt() } }
        .sortedBy { it[1] }
        .map { it[0] }
        .toIntArray()
    dp = IntArray(n)
}

fun solve() {
    for (i in 0 until n) {
        dp[i] = 1
        for (j in 0 until i) {
            if (lines[i] > lines[j]) {
                dp[i] = maxOf(dp[i], dp[j] + 1)
            }
        }
    }

    println(n - dp.maxOf { it })
}

fun main() {
    input()
    solve()
}