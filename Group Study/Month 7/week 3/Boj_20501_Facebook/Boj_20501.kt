import java.util.*
import kotlin.collections.ArrayList

var n = 0
var m = 0
lateinit var graph: Array<HashSet<Int>>
lateinit var ques: ArrayList<Pair<Int, Int>>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    graph = Array(n + 1) { hashSetOf() }

    for (i in 1..n) {
        val line = readLine()
        for (j in 1..n) {
            if (line[j - 1] == '1') {
                graph[i].add(j)
                graph[j].add(i)
            }
        }
    }

    m = readLine().toInt()
    ques = arrayListOf()

    repeat(m) {
        val (a, b) = readLine().split(" ").map { it.toInt() }
        ques.add(a to b)
    }
}

fun solve() {
    val sb = StringBuilder()

    ques.forEach { pair ->

    }

    println(sb)
}

fun main() {
//    input()
//    solve()

    val a = "0110"
    val b = "1011"
}