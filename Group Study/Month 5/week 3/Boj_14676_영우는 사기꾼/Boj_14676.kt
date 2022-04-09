import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.StringTokenizer

var n = 0
var m = 0
var k = 0

lateinit var graph: Array<HashSet<Int>>
lateinit var needs: Array<HashSet<Int>>
lateinit var count: IntArray

val info = LinkedList<Int>()

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    k = st.nextToken().toInt()

    graph = Array(n + 1) { hashSetOf() }
    needs = Array(n + 1) { hashSetOf() }
    count = IntArray(n + 1)

    while (m --> 0) {
        st = StringTokenizer(readLine())
        var from = st.nextToken().toInt()
        var to = st.nextToken().toInt()

        graph[from].add(to)
        needs[to].add(from)
    }
    while (k --> 0) {
        st = StringTokenizer(readLine())
        info.add(st.nextToken().toInt())
        info.add(st.nextToken().toInt())
    }
}

fun solve() {
    while (info.isEmpty().not()) {
        val action = info.poll()
        val target = info.poll()

        if (!check(action, target)) {
            print("Lier!")
            return
        }
    }
    print("King-God-Emperor")
}

fun check(action: Int, target: Int) : Boolean {
    return when(action) {
        1 -> checkBuild(target)
        else -> checkDestroy(target)
    }
}

fun checkBuild(target: Int) : Boolean {
    if (needs[target].size > 0) return false

    if (count[target]++ == 0) {
        graph[target].forEach {
            needs[it].remove(target)
        }
    }
    return true
}

fun checkDestroy(target: Int) : Boolean {
    if (count[target] <= 0) return false

    if (--count[target] == 0) {
        graph[target].forEach {
            needs[it].add(target)
        }
    }
    return true
}

fun main() {
    input()
    solve()
}