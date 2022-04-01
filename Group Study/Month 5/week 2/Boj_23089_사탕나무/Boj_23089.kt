import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

var n = 0
var k = 0

lateinit var graph: Array<ArrayList<Int>>
lateinit var dp: Array<IntArray>
lateinit var size: IntArray

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    readLine().split(" ")
        .also {
            n = it[0].toInt()
            k = it[1].toInt()
        }

    graph = Array(n + 1) { arrayListOf() }
    dp = Array(k + 1) { IntArray(n + 1) }
    size = IntArray(n + 1)

    for (i in 1 until n) {
        readLine().split(" ")
            .also {
                val from = it[0].toInt()
                val to = it[1].toInt()

                graph[from].add(to)
                graph[to].add(from)
            }
    }

    for (i in 1..n) size[i] = graph[i].size
}

fun solve() {
    // dp[dist][x] => x번 사탕으로부터 dist 만큼 떨어진 범위 내의 모든 사탕의 개수
    for (i in 1..n) {
        dp[0][i] = 1
        dp[1][i] = 1 + graph[i].size
    }

    for (dist in 2..k) {
        for (i in 1..n) {
            graph[i].forEach { to ->
                dp[dist][i] += dp[dist - 1][to]
            }
            dp[dist][i] -= dp[dist - 2][i] * (size[i] - 1)
        }
    }

    println("size: ${size.contentToString()}")
    for (dist in 0..k) {
        println("\ndist = $dist")
        for (i in 1..n) {
            print("${dp[dist][i]} ")
        }
        println()
    }

    var ans = 0
    for (i in 1..n) {
        ans = max(ans, dp[k][i])
    }
    print(ans)
}

fun main() {
    input()
    solve()
}