import java.util.PriorityQueue

data class Jewelry(
    val value: Int,
    val weight: Int,
)

var n = 0
var k = 0
lateinit var bags: List<Int>
lateinit var jewelries: List<Jewelry>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        k = this[1]
    }
    jewelries = List(n) {
        val (weight, value) = readLine().split(" ").map { it.toInt() }
        Jewelry(value, weight)
    }.sortedBy { it.weight }

    bags = (0 until k).map { readLine().toInt() }.sorted()
}

fun solve() {
    val que = PriorityQueue<Jewelry>() { j1, j2 -> j2.value - j1.value }
    var index = 0
    var sum = 0L

    bags.forEach { bag ->
        while (index < n && bag >= jewelries[index].weight) {
            que.add(jewelries[index++])
        }
        if (que.isEmpty()) return@forEach
        sum += que.poll().value
    }

    println(sum)
}

fun main() {
    input()
    solve()
}