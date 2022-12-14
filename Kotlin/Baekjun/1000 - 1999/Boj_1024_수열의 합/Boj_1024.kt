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
        val temp = n - (length * (length + 1) / 2)
        if (temp % length == 0) {
            val x = temp / length + 1

            if (x >= 0) {
                val builder = StringBuilder()
                for (i in 0 until length) {
                    builder.append(x + i).append(' ')
                }
                println(builder)
                return
            }
        }
    }
    println(-1)
}

fun main() {
    input()
    solve()
}
