import java.util.*

var n = 0
var m = 0
var k = 0
lateinit var nums: LongArray
lateinit var commands: Array<String>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        k = nextToken().toInt()
    }
    nums = LongArray(n + 1) { if (it == 0) 0 else readLine().toLong() }
    commands = Array(m + k) { readLine() }
}

fun solve() {
    val sb = StringBuilder()

    commands.forEach { command ->
        val (a, b, c) = command.split(" ").map { it.toLong() }
        if (a == 1L) {

        } else {

        }
    }

    println(sb)
}

fun main() {
    input()
    solve()
}