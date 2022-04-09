import java.io.*
import java.util.*
import kotlin.collections.HashMap

val target = "123456780"
var state = ""
lateinit var dist : HashMap<String, Int>

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val sb = StringBuilder()
    for (i in 0 until 3) {
        StringTokenizer(readLine()).apply {
            sb.append(nextToken())
            sb.append(nextToken())
            sb.append(nextToken())
        }
    }

    state = sb.toString()
    dist = hashMapOf()
}

fun solve() {
    val dir = arrayOf(
        arrayOf(1, 0),
        arrayOf(-1, 0),
        arrayOf(0, 1),
        arrayOf(0, -1),
    )
    val que = LinkedList<String>()
    que.add(state)
    dist[state] = 0

    while (que.isNotEmpty()) {
        val now = que.poll()
        val index = now.indexOf('0')

        if (now == target) break

        for (i in 0 until 4) {
            val row = (index / 3) + dir[i][0]
            val col = (index % 3) + dir[i][1]

            if (row < 0 || row >= 3 || col < 0 || col >= 3) continue

            val nextIndex = row * 3 + col
            val next = now
                .replaceRange(index, index + 1, now[nextIndex].toString())
                .replaceRange(nextIndex, nextIndex + 1, "0")

            if (!dist.containsKey(next)) {
                que.add(next)
                dist[next] = dist[now]!! + 1
            }
        }
    }

    println(dist[target] ?: -1)
}

fun main() {
    input()
    solve()
}

