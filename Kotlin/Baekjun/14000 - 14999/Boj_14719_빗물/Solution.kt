import java.util.LinkedList

var h = 0
var w = 0
lateinit var heights: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        h = it[0]
        w = it[1]
    }
    heights = readLine().split(" ").map { it.toInt() }.toIntArray()
}

fun solve() {
    var sum = 0

    for (i in 1 until w - 1) {
        sum += maxOf(
            0,
            minOf(findLeft(i), findRight(i)) - heights[i]
        )
    }

    println(sum)
}

fun findLeft(i: Int): Int = heights.filterIndexed { x, height -> height > heights[i] && x < i }.maxOrNull() ?: 0

fun findRight(i: Int): Int = heights.filterIndexed { x, height -> height > heights[i] && x > i }.maxOrNull() ?: 0

fun main() {
    input()
    solve()
}