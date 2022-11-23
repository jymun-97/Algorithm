import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()
var ans = Double.MAX_VALUE

data class Point(
    val x: Int,
    val y: Int,
)

var n = 0
lateinit var points: List<Point>

fun input() = with(reader) {
    n = readLine().toInt()
    points = List(n) {
        val tokens = readLine().split(" ").map { it.toInt() }
        Point(tokens[0], tokens[1])
    }
}

fun solve() {
    ans = Double.MAX_VALUE
    dfs(0, hashSetOf(), hashSetOf())
    builder.append(ans).append('\n')
}

fun dfs(k: Int, start: HashSet<Point>, end: HashSet<Point>) {
    if (k == n) {
        if (start.size != end.size) return

        val x = end.sumOf { it.x } - start.sumOf { it.x }
        val y = end.sumOf { it.y } - start.sumOf { it.y }
        ans = min(
            ans, sqrt(
                (x + 0.0).pow(2) + (y + 0.0).pow(2)
            )
        )
        return
    }
    val target = points[k]
    start.add(target)
    dfs(k + 1, start, end)
    start.remove(target)

    end.add(target)
    dfs(k + 1, start, end)
    end.remove(target)
}

fun main() = with(reader) {
    repeat(readLine().toInt()) {
        input()
        solve()
    }
    println(builder)
}