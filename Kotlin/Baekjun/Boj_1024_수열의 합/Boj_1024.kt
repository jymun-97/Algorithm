import java.util.*

var n = 0
var l = 0

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        l = nextToken().toInt()
    }
}

fun solve() {
    for (length in l..100) {
        var left = 0
        var right = left + length - 1

        while (left <= 10000) {
            val sum =  (rightgit  * right + right - left * left - left) / 2
            when {
                sum == n -> {
                    println(
                        (left..right).joinToString(" ")
                    )
                    return
                }

                sum > n -> break

                else -> {
                    left++
                    right++
                }
            }
        }
    }
}

fun main() {
    input()
    solve()
}
