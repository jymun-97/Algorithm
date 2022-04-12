import java.util.*
import kotlin.collections.HashMap
import kotlin.math.max

var n = 0
var root = 0
var giga = 0

lateinit var graph: Array<HashMap<Int, Int>>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        root = nextToken().toInt()
    }

    graph = Array(n + 1) { hashMapOf() }

    for (i in 1 until n) {
        StringTokenizer(readLine()).apply {
            val from = nextToken().toInt()
            val to = nextToken().toInt()
            val weight = nextToken().toInt()

            graph[from][to] = weight
            graph[to][from] = weight
        }
    }
}

fun solve() {
    bfs()
    println("${dfsFromRoot(root)} ${dfsFromGiga(giga)}")
}

fun bfs() {
    val que = LinkedList<Int>()
    que.add(root)

    while (que.isNotEmpty()) {
        val from = que.poll()

        graph[from].forEach {
            val to = it.key

            graph[to].remove(from)
            que.add(to)
        }
    }
}

fun dfsFromRoot(from: Int): Int {
    if (graph[from].size != 1) {
        giga = from
        return 0
    }
    var pillar = 0
    graph[from].forEach {
        val to = it.key
        pillar += dfsFromRoot(to) + graph[from][to]!!
    }
    return pillar
}

fun dfsFromGiga(from: Int): Int {
    if (graph[from].size == 0) return 0

    var branch = 0
    graph[from].forEach {
        val to = it.key

        val cand = dfsFromGiga(to) + graph[from][to]!!
        branch = max(cand, branch)
    }
    return branch
}

fun main() {
    input()
    solve()
}