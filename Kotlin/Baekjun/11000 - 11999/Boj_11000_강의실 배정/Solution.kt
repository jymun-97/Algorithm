import java.util.PriorityQueue

data class Range(
    val start: Int,
    val end: Int,
)

var n = 0
lateinit var classes: List<Range>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    classes = List(n) {
        val (start, end) = readLine().split(" ").map { it.toInt() }
        Range(start, end)
    }.sortedWith(compareBy( { it.start }, { it.end } ))
}

fun solve() {
    var i = 0
    var max = 0
    val que = PriorityQueue<Range>() { r1, r2 -> r1.end - r2.end}

    while (i < n) {
        max = maxOf(max, que.size)
        val target = classes[i]

        when {
            que.isEmpty() || que.peek().end > target.start-> {
                que.add(target)
                i++
            }
            else -> que.poll()
        }
    }

    println(max)
}

fun main() {
    input()
    solve()
}