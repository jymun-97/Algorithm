import java.io.*
import java.util.*

var n = 0
var m = 0

lateinit var arr: CharArray
lateinit var sb: StringBuilder

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }
    arr = CharArray(m)
    StringTokenizer(readLine()).apply {
        for (i in 0 until m) arr[i] = nextToken()[0]
    }
    sb = StringBuilder()
}

fun solve() {
    arr.sort()

    dfs(0, -1, "")
    println(sb)
}

fun dfs(k: Int, pre: Int, str: String) {
    if (k == n) {
        if (isValid(str)) sb.append(str).append('\n')
        return
    }
    for (i in pre + 1 until m) {
        dfs(k + 1, i, str + arr[i])
    }
}

fun isValid(str: String) : Boolean {
    val count = arrayOf(0, 0)
    str.forEach { c ->
        when (c) {
            'a','e','i','o','u' -> count[0]++
            else -> count[1]++
        }
    }
    return count[0] >= 1 && count[1] >= 2
}

fun main() {
    input()
    solve()
}