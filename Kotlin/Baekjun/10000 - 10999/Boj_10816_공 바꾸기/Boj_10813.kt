import java.util.*

var n = 0
var m = 0
lateinit var arr : IntArray

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    arr = IntArray(n + 1) { i -> i }

    repeat(m) {
        val (i, j) = readLine().split(" ").map { it.toInt() }
        arr[i] = arr[j].also { arr[j] = arr[i] }
    }
}

fun solve() {
    val ans = StringBuilder()
    for (i in 1 .. n) ans.append(arr[i]).append(' ')

    println(ans)
}

fun main() {
    input()
    solve()
}