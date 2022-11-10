import java.util.*

const val MAX = 100_000
var n = 0
var k = 0
val dist = IntArray(MAX + 1) { Int.MAX_VALUE }

data class Info(
    val from: Int,
    val dist: Int
)

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        k = nextToken().toInt()
    }
}

fun solve() {
    val que = PriorityQueue<Info>() { o1, o2 -> o1.dist - o2.dist }
    que.add(Info(n, 0))
    dist[n] = 0

    while (true) {
        val info = que.poll()

        if (info.dist > dist[info.from]) continue
        if (info.from == k) break

        (0..2).forEach {
            val (next, weight) = when (it) {
                0 -> info.from - 1 to 1
                1 -> info.from + 1 to 1
                else -> info.from * 2 to 0
            }

            if (next !in 0..MAX) return@forEach
            if (dist[next] > dist[info.from] + weight) {
                dist[next] = dist[info.from] + weight
                que.add(Info(next, dist[next]))
            }
        }
    }

    println(dist[k])
}

fun main() {
    input()
    solve()
}