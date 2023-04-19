var n = 0
lateinit var need: IntArray
lateinit var pay: IntArray
lateinit var dp: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    need = IntArray(n + 1)
    pay = IntArray(n + 1)
    dp = IntArray(n + 2)

    for (i in 1..n) readLine().split(" ").map { it.toInt() }.let {
        need[i] = it[0]
        pay[i] = it[1]
    }
}

fun solve() {
    for (i in 1..n + 1) {
        for (j in i - 50 until i) {
            if (j < 1) continue

            dp[i] = maxOf(dp[i], dp[j])
            if (need[j] + j <= i) {
                dp[i] = maxOf(
                    dp[i],
                    dp[j] + pay[j]
                )
            }
        }
    }
    println(dp[n + 1])
}

fun main() {
    input()
    solve()
}