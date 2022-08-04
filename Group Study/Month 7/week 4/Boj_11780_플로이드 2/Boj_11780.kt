import java.util.*
import kotlin.math.min

var n = 0
var m = 0
lateinit var dist: Array<LongArray>
lateinit var next: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    m = readLine().toInt()

    dist = Array(n + 1) { LongArray(n + 1) { Int.MAX_VALUE.toLong() } }
    next = Array(n + 1) { IntArray(n + 1) { -1 } }

    for (i in 1 .. n) dist[i][i] = 0
    repeat(m) {
        val (from, to, weight) = readLine().split(" ").map { it.toInt() }
        dist[from][to] = min(dist[from][to], weight.toLong())
        next[from][to] = from
    }
}

fun solve() {
    floyd()

    val ans = StringBuilder()
    for (i in 1 .. n) {
        for (j in 1 .. n) {
            ans.append(if (dist[i][j] >= Int.MAX_VALUE) 0 else dist[i][j]).append(' ')
        }
        ans.append('\n')
    }

    for (i in 1 .. n) {
        for (j in 1 .. n) {
            if (next[i][j] == -1) ans.append(0)
            else {
                val path = Stack<Int>()
                var pre = j
                path.push(pre)

                while (i != next[i][pre]) {
                    pre = next[i][pre]
                    path.push(pre)
                }

                path.push(i)
                ans.append(path.size).append(' ')
                while (path.isNotEmpty()) ans.append(path.pop()).append(' ')
            }
            ans.append('\n')
        }
    }

    println(ans)
}

fun floyd() {
    for (k in 1 .. n) {
        for (i in 1 .. n) {
            for (j in 1 .. n) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j]
                    next[i][j] = next[k][j]
                }
            }
        }
    }
}

fun main() {
    input()
    solve()
}