import java.util.*

var n = 0
var m = 0
var loc = LinkedList<Int>()

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    loc.addAll(readLine().split(' ').map { it.toInt() }.sorted())
}

fun solve() {
    var ans = 0
    var flag = false
    while (loc.isNotEmpty()) {
        if (loc.first > 0) {
            ans += if (flag) loc.last * 2 else loc.last
            for (i in 0 until m) if (loc.isNotEmpty()) loc.pollLast()
        }
        else if (loc.last < 0) {
            ans += if (flag) -loc.first * 2 else -loc.first
            for (i in 0 until m) if (loc.isNotEmpty()) loc.poll()
        }
        else {
            if (-1 * loc.first > loc.last) {
                ans += if (flag) -loc.first * 2 else -loc.first
                dropFirst()
            }
            else {
                ans += if (flag) loc.last * 2 else loc.last
                dropLast()
            }
        }
        flag = true
    }
    println(ans)
}

fun dropFirst() {
    for (i in 0 until m) {
        if (loc.first < 0) loc.poll()
        else break
    }
}

fun dropLast() {
    for (i in 0 until m) {
        if (loc.last > 0) loc.pollLast()
        else break
    }
}

fun main() {
    input()
    solve()
}