import java.util.PriorityQueue

data class Lecture(
    val start: Int,
    val end: Int,
)

var n = 0
lateinit var lectures: List<Lecture>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    lectures = List(n) {
        val (_, start, end) = readLine().split(" ").map { it.toInt() }
        Lecture(start, end)
    }.sortedBy { it.start }
}

fun solve() {
    var ans = 0
    val que = PriorityQueue<Lecture>() { l1, l2 -> l1.end - l2.end }

    for (lecture in lectures) {
        if (que.isEmpty() || lecture.start < que.peek().end) ans++
        else que.poll()

        que.add(lecture)
    }

    println(ans)
}

fun main() {
    input()
    solve()
}