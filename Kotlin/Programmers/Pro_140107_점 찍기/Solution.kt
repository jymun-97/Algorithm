import kotlin.math.*

fun Int.square() = toLong() * toLong()
fun Long.sqrt() = sqrt(toDouble())

class Solution {
    fun solution(k: Int, d: Int): Long {
        return (0..d / k)
            .map { it * k }
            .sumOf { x ->
                val y = (d.square() - x.square()).sqrt().toLong() / k * k
                y / k + 1
            }
    }
}