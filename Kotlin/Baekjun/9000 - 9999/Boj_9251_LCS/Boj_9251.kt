import java.util.*
import kotlin.math.max

var a = ""
var b = ""
lateinit var dp: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    a = readLine()
    b = readLine()
    dp = Array(a.length + 1) { IntArray(b.length + 1) { -1 } }
}

fun solve() {
    println(lcs(0, 0))
}

fun lcs(l: Int, r: Int): Int {
    if (dp[l][r] != -1) return dp[l][r]

    var left = l
    var right = r
    var count = 0

    while (left < a.length && right < b.length) {
        if (a[left] == b[right]) {
            left++
            right++
            count++
        }
        else {
            count += max(lcs(left + 1, right), lcs(left, right + 1))
            break
        }
    }

    return count.also { dp[l][r] = it }
}

fun main() {
    input()
    solve()
}