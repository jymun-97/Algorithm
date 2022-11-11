import java.util.*

var n = 0
var m = 0
var l = 0
lateinit var points: List<Int>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        l = nextToken().toInt()
    }
    points = if (n > 0) "0 ${readLine()} $l".split(" ").map { it.toInt() }.sorted()
            else listOf(0, l)
}

fun solve() {
    var left = 1
    var right = l - 1
    var ans = -1

    while (left <= right) {
        val mid = (left + right) / 2
        val count = build(mid)

        when {
            count <= m -> {
                right = mid - 1
                ans = mid
            }
            else -> left = mid + 1
        }
    }

    println(ans)
}

fun build(dist: Int): Int {
    var count = 0
    for (i in 0 until points.lastIndex) {
        val diff = points[i + 1] - points[i]
        count += diff / dist + if (diff % dist == 0) -1 else 0
    }
    return count
}

fun main() {
    input()
    solve()
}