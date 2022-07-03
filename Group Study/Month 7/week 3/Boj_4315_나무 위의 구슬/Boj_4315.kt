import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.math.abs

data class Node(
    val id: Int,
    var count: Int,
    val child: HashSet<Int>
)
var n = 0
lateinit var nodes: ArrayList<Node>
lateinit var parentOf: IntArray

val sb = StringBuilder()
val br = System.`in`.bufferedReader()

fun input() {
    nodes = arrayListOf()
    repeat(n) {
        val st = StringTokenizer(br.readLine())
        nodes.add(
            Node(
                id = st.nextToken().toInt() - 1,
                count = st.nextToken().toInt(),
                child = IntArray(st.nextToken().toInt()) { st.nextToken().toInt() - 1 }.toHashSet()
            )
        )
    }
    parentOf = IntArray(n) { -1 }
    nodes.forEach { node ->
        node.child.forEach { child ->
            parentOf[child] = node.id
        }
    }
}

fun solve() {
    val que = LinkedList<Node>()
    que.addAll(nodes.filter { it.child.isEmpty() })

    var ans = 0
    while (que.isNotEmpty()) {
        val node = que.poll()
        if (parentOf[node.id] == -1) break

        val parent = nodes[parentOf[node.id]]
        parent.apply {
            child.remove(node.id)
            count += (node.count - 1)
            ans += abs(node.count - 1)
        }

        if (parent.child.isEmpty()) que.add(parent)
    }

    sb.append(ans).append('\n')
}

fun main() {

    while (true) {
        n = br.readLine().toInt()

        if (n == 0) break

        input()
        solve()
    }
    println(sb.toString())
}