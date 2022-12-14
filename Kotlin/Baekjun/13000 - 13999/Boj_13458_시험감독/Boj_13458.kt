import java.util.*

var n = 0
var b = 0
var c = 0
lateinit var nums : IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine().split(' ').map { it.toInt() }.toIntArray()

    StringTokenizer(readLine()).apply {
        b = nextToken().toInt()
        c = nextToken().toInt()
    }
}

fun solve() {
    var ans = 0L
    nums.forEach {
        ans++
        val remain = it - b

        if (remain > 0) {
            ans += if (remain % c == 0) remain / c else remain / c + 1
        }
    }
    println(ans)
}

fun main() {
    input()
    solve()
}