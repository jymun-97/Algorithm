import java.io.*
import java.util.*

lateinit var a: IntArray
var n = 0
var m = 0

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    a = IntArray(n + 1)
    StringTokenizer(readLine()).apply {
        for (i in 1 .. n) a[i] = nextToken().toInt()
    }

    for (i in 0 until m) {
        StringTokenizer(readLine()).apply {
            val command = nextToken().toInt()
            val first = nextToken().toInt()
            val second = nextToken().toInt()

            solve(command, first, second)
        }
    }
}

fun solve(command: Int, first: Int, second: Int) {
    when(command) {
        1 -> {
            a[first] = second
        }
        2 -> {
            for (i in first .. second) a[i] = 1 - a[i]
        }
        3 -> {
            for (i in first .. second) a[i] = 0
        }
        else -> {
            for (i in first .. second) a[i] = 1
        }
    }
}

fun main() {
    input()

    for (i in 1 .. n) print("${a[i]} ")
}