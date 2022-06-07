import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.max

data class Info(
    var start: Int,
    var end: Int
)
var n = 0
var m = 0
lateinit var list: ArrayList<Info>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    list = arrayListOf()

    repeat(n) {
        StringTokenizer(readLine()).apply {
            val start = nextToken().toInt()
            val end = nextToken().toInt()

            if (start > end) list.add(Info(end, start))
        }
    }
}

fun solve() {
    list.sortBy { it.start }

    val size = list.size
    var ans = m.toLong()
    var idx = 0

    while (idx < size) {
        val info = list[idx]

        while (idx < size && list[idx].start in info.start .. info.end) {
            info.end = max(info.end, list[idx++].end)
        }

        ans += (info.end - info.start) * 2
    }

    println(ans)
}

fun main() {
    input()
    solve()
}