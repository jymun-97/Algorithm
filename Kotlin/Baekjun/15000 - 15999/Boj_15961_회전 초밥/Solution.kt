import java.util.LinkedList

var n = 0
var d = 0
var k = 0
var c = 0
lateinit var table: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        d = it[1]
        k = it[2]
        c = it[3]
    }
    table = IntArray(n) { readLine().toInt() }
}

fun solve() {
    var max = 0
    val window = LinkedList<Int>()
    val map = hashMapOf<Int, Int>()

    for (i in 0 until k) {
        val target = table[i]
        if (!map.contains(target)) max++

        window.add(target)
        map[target] = (map[target] ?: 0) + 1
    }
    var count = max
    if (!map.contains(c)) max++

    for (i in k until n + k) {
        val removed = window.pollFirst()

        map[removed] = map[removed]!! - 1
        if (map[removed]!! == 0) {
            map.remove(removed)
            count--
        }

        val target = if (i < n) table[i] else table[i % n]
        if (!map.contains(target)) count++

        window.add(target)
        map[target] = (map[target] ?: 0) + 1

        max = maxOf(
            max,
            count + if (!map.contains(c)) 1 else 0
        )
    }

    println(max)
}

fun main() {
    input()
    solve()
}