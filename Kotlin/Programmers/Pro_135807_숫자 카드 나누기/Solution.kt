import java.util.LinkedList
import kotlin.math.sqrt

class Solution {
    fun solution(arrayA: IntArray, arrayB: IntArray): Int = maxOf(
        getTarget(arrayA.gcd(), arrayB),
        getTarget(arrayB.gcd(), arrayA)
    )

    fun getTarget(gcd: Int, others: IntArray): Int = gcd.divisors().findLast { isValid(it, others) } ?: 0

    fun isValid(divisor: Int, dividends: IntArray) = dividends.none { it % divisor == 0 }
}

fun Int.gcdWith(other: Int): Int {
    val high = maxOf(this, other)
    val low = minOf(this, other)

    return if (low == 0) high else low.gcdWith(high % low)
}

fun IntArray.gcd(): Int = fold(this[0]) { acc, num -> acc.gcdWith(num) }

fun Int.divisors(): List<Int> = (sqrt(toDouble()).toInt() downTo 1).fold(LinkedList()) { result, cand ->
    if (this % cand == 0) {
        result.addFirst(cand)
        if (cand * cand != this) result.addLast(this / cand)
    }
    result
}