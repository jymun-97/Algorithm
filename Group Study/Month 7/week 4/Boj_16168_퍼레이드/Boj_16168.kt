import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val graph = Array(n + 1) { hashSetOf<Int>() }
    val indeg = IntArray(n + 1)

    repeat(m) {
        val (from, to) = readLine().split(" ").map { it.toInt() }
        graph[from].add(to).also { graph[to].add(from) }
        indeg[from]++.also { indeg[to]++ }
    }

    var count = 1
    val visit = BooleanArray(n + 1)
    val que = LinkedList<Int>()
    que.add(1)
    visit[1] = true

    while (que.isNotEmpty()) {
        val from = que.poll()

        graph[from].forEach { to ->
            if (!visit[to]) {
                que.add(to)
                visit[to] = true
                count++
            }
        }
    }

    if (count != n) {
        println("NO")
        return
    }
    println(
        when (indeg.count { it % 2 == 1 }) {
            0, 2 -> "YES"

            else -> "NO"
        }
    )
}