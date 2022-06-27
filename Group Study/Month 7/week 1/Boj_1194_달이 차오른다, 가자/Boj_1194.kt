import java.util.*
import kotlin.collections.HashSet

data class State(
    val row: Int,
    val col: Int,
    val keys: HashSet<Char>
)

var n = 0
var m = 0
lateinit var map : Array<StringBuilder>
lateinit var dist : HashMap<State, Int>
lateinit var start : State

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    map = Array(n) { StringBuilder(readLine()) }
    dist = hashMapOf()
}

fun solve() {
    init()

    var ans = -1
    val que = LinkedList<State>()
    que.add(start)
    dist[start] = 0

    while (que.isNotEmpty()) {
        val from = que.poll()

        if (map[from.row][from.col] == '1') {
            ans = dist[from]!!
            break
        }

        for (i in 0 until 4) {
            val nr = from.row + dir[i][0]
            val nc = from.col + dir[i][1]

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue
            if (map[nr][nc] == '#') continue
            if (map[nr][nc].isUpperCase() && !from.keys.contains(map[nr][nc].lowercaseChar())) continue

            val next = State(nr, nc, HashSet(from.keys))
            next.keys.add(map[nr][nc].lowercaseChar())

            if (dist[next] != null) continue

            que.add(next)
            dist[next] = dist[from]!! + 1
        }
    }

    println(ans)
}

fun init() {
    map.forEachIndexed { row, line ->
        if (line.contains('0')) {
            start = State(row, line.indexOf('0'), hashSetOf('.', '1'))
            return
        }
    }
}

fun main() {
    input()
    solve()
}