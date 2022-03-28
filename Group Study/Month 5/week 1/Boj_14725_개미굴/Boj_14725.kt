import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList

data class Node(
    val value: String,
    val next: ArrayList<Node> = arrayListOf()
) {
    override fun equals(other: Any?): Boolean = value == (other as Node).value
}

var root = Node("")
val sb = StringBuilder()

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var n = readLine().toInt()

    while (n-- > 0) {
        val st = StringTokenizer(readLine())

        var k = st.nextToken().toInt()
        var node = root
        while (k-- > 0) {
            var next = Node(st.nextToken())
            if (!node.next.contains(next))
                node.next.add(next)

            node = node.next[node.next.indexOf(next)]
        }
    }
}

fun solve() {
    root.next.apply {
        sortBy { node -> node.value }
        forEach {
            dfs(it, "")
        }
    }

    print(sb)
}

fun dfs(from: Node, level: String) {
    sb.append(level).append(from.value).append('\n')

    from.next.apply {
        sortBy { node -> node.value }
        forEach { to ->
            dfs(to, "$level--")
        }
    }
}

fun main() {
    input()
    solve()
}