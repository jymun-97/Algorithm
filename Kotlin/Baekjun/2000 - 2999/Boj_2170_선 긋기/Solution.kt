
data class Range(
    val start: Int,
    val end: Int
) {
    val size = end - start

    companion object {
        fun isCollapsed(a: Range, b: Range) = b.start in a.start..a.end

        fun merge(a: Range, b: Range) = Range(
            start = a.start,
            end = maxOf(a.end, b.end)
        )
    }
}

var n = 0
lateinit var ranges: List<Range>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    ranges = List(n) {
        val (start, end) = readLine().split(" ").map { it.toInt() }
        Range(start, end)
    }.sortedBy { it.start }
}

fun solve() {
    var sum = 0L
    var pre = ranges[0]
    for (i in 1 until n) {
        if (Range.isCollapsed(pre, ranges[i])) pre = Range.merge(pre, ranges[i])
        else {
            sum += pre.size
            pre = ranges[i]
        }
    }
    sum += pre.size

    println(sum)
}

fun main() {
    input()
    solve()
}