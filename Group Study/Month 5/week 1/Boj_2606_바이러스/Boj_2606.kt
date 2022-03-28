import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

lateinit var graph: Array<ArrayList<Int>>
lateinit var visit: Array<Boolean>

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var n = readLine().toInt()
    var m = readLine().toInt()

    graph = Array(n + 1) { arrayListOf() }
    visit = Array(n + 1) { false }

    while (m --> 0) {
        val st = StringTokenizer(readLine())
        val from = st.nextToken().toInt()
        val to = st.nextToken().toInt()

        graph[from].add(to)
        graph[to].add(from)
    }
}

fun solve() {
    var answer = 0
    val que = LinkedList<Int>()
    que.add(1)
    visit[1] = true

    while (que.isNotEmpty()) {
        val from = que.poll()

        graph[from].forEach { to ->
            if (!visit[to]) {
                que.add(to)
                visit[to] = true
                answer++
            }
        }
    }

    print(answer)
}

fun main() {
    input()
    solve()
}