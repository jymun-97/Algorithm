import java.io.*
import java.util.*
import kotlin.math.min

var n = 0L
var k = 0

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toLong()
    k = readLine().toInt()
}

fun solve() {
    var left = 1L
    var right = n * n
    var ans = 0L

    while (left <= right) {
        val mid = (left + right) / 2

        if (isValid(mid)) {
            right = mid - 1
            ans = mid
        }
        else left = mid + 1
    }

    println(ans)
}

fun isValid(target: Long) = k <= getCount(target)

fun getCount(target: Long) : Long {
    var sum = 0L
    for (i in 1 .. n) {
        if (i > target) break
        sum += min(target / i, n)
    }
    return sum
}

fun main() {
    input()
    solve()
}