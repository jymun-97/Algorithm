import java.util.LinkedList

var n = 0
var m = 0
val exceptions = hashSetOf<Int>()
lateinit var visit: Array<BooleanArray>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    visit = Array(n + 1) { BooleanArray(200) }
    repeat(m) {
        exceptions.add(readLine().toInt())
    }
}

fun solve() {
    val que = LinkedList<Int>()
    que.add(1)
    que.add(0)
    que.add(0)
    visit[1][0] = true

    while (que.isNotEmpty()) {
        val from = que.poll()
        val dist = que.poll()
        val jump = que.poll()

        if (from == n) {
            println(dist)
            return
        }

        listOf(jump - 1, jump, jump + 1).forEach { offset ->
            val to = from + offset

            if (to !in from + 1 .. n || visit[to][offset] || exceptions.contains(to)) return@forEach

            que.add(to)
            que.add(dist + 1)
            que.add(offset)

            visit[to][offset] = true
        }
    }

    println(-1)
}

fun main() {
    input()
    solve()
}