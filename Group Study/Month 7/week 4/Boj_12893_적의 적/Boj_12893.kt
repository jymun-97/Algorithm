import java.util.*

var n = 0
var m = 0
lateinit var people : IntArray
lateinit var graph : Array<ArrayList<Int>>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.also {
        n = it[0]
        m = it[1]
    }
    people = IntArray(n + 1) { -1 }
    graph = Array(n + 1) { arrayListOf() }

    repeat(m) {
        val (from, to) = readLine().split(" ").map { it.toInt() }

        graph[from].add(to)
        graph[to].add(from)
    }
}

fun solve() {
    for (i in 1 .. n) {
        if (people[i] == -1) {
            val response = bfs(i)

            if (!response) {
                println(0)
                return
            }
        }
    }

    println(1)
}

fun bfs(start: Int) : Boolean {
    val que = LinkedList<Int>()

    que.add(start)
    people[start] = 0

    while (que.isNotEmpty()) {
        val from = que.poll()

        graph[from].forEach { to ->
            when (people[to]) {
                -1 -> {
                    que.add(to)
                    people[to] = 1 - people[from]
                }

                people[from] -> return false

                else -> return@forEach
            }
        }
    }

    return true
}

fun main() {
    input()
    solve()
}