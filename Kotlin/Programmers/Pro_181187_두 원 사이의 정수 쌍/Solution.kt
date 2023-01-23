import kotlin.math.sqrt

fun Int.square() = this * toLong()

fun Long.sqrt() = sqrt(toDouble())

class Solution {
    fun solution(r1: Int, r2: Int): Long = (0..r2).sumOf { x ->
        val y1 = (r1.square() - x.square()).sqrt().toLong()
        val y2 = (r2.square() - x.square()).sqrt().toLong()

        (y2 - y1) * 4 + if (y1.toDouble() == (r1.square() - x.square()).sqrt()) 4 else 0
    } - 4
}