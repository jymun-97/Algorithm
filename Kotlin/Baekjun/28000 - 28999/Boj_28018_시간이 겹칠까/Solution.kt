
var n = 0
var q = 0
lateinit var sum: IntArray
lateinit var time: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    sum = IntArray(1_000_002)
    repeat(n) {
        val (start, end) = readLine().split(" ").map { it.toInt() }
        sum[start]++
        sum[end + 1]--
    }

    q = readLine().toInt()
    time = readLine().split(" ").map { it.toInt() }.toIntArray()
}

fun solve() {
    for (i in 0 until sum.lastIndex) {
        sum[i + 1] += sum[i]
    }
    println(
        buildString {
            time.forEach {
                appendLine(sum[it])
            }
        }
    )
}

fun main() {
    input()
    solve()
}