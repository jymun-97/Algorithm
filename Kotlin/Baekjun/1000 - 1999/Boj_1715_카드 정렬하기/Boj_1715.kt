import java.util.PriorityQueue

var n = 0
lateinit var cards: List<Int>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    cards = List(n) { readLine().toInt() }.sorted()
}

fun solve() {
    var sum = 0
    val que = PriorityQueue<Int>().apply {
        addAll(cards)
    }
    while (que.size > 1) {
        val temp = que.poll() + que.poll()

        sum += temp
        que.add(temp)
    }
    println(sum)
}

fun main() {
    input()
    solve()
}