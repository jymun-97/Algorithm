import kotlin.math.min

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m, k) = readLine().split(" ").map { it.toLong() }
    val needs = listOf(0).plus(readLine().split(" ").map { it.toInt() })

    val list = arrayListOf<Pair<Int, Int>>()
    for (i in 0 until m) {
        var (from, to) = readLine().split(" ").map { it.toInt() }

        if (from > to && from != n.toInt()) to = from.also { from = to }
        list.add(from to to)
    }

    if (m <= 1) {
        println("YES")
        return
    }

    var pre = 1
    var sum = 0L
    list.sortedBy { it.first }.forEach {
        var min = Int.MAX_VALUE
        for (i in pre .. it.first) min = min(needs[i], min)

        if (pre == 1 && list.last().second != 1) {
            for (i in n.toInt() downTo list.last().second) min = min(needs[i], min)
        }
        sum += min
        pre = it.second
    }

    println(if (sum <= k) "YES" else "NO")
}