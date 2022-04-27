import java.io.*
import java.util.*

var n = 0
var m = 0
lateinit var arr: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    arr = IntArray(n)
    for (i in 0 until n) arr[i] = readLine().toInt()
}

fun solve() {
    arr.sort()

    var left = 0
    var right = arr.last()
    var ans = -1

    while (left <= right) {
        val mid = (left + right) / 2

        var target = arr[0]
        var count = 1

        for (i in 1 until n) {
            if (arr[i] - target >= mid) {
                target = arr[i]
                count++
            }
        }

        if (count >= m) {
            ans = mid
            left = mid + 1
        }
        else right = mid - 1
    }

    println(ans)
}

fun main() {
    input()
    solve()
}