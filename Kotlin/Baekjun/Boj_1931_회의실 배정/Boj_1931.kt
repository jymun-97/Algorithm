import java.util.*

data class Range(
    val start: Int,
    val end: Int,
)

var n = 0
lateinit var ranges: List<Range>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    ranges = List(n) {
        val (start, end) = readLine().split(" ").map { it.toInt() }
        Range(start, end)
    }.sortedWith(compareBy({ it.end }, { it.start }))
}

fun solve() {
    var ans = 1
    var pre = ranges[0]
    for (i in 1 until n) {
        if (ranges[i].start < pre.end) continue

        pre = ranges[i]
        ans++
    }
    println(ans)
}

fun main() {
    input()
    solve()
}