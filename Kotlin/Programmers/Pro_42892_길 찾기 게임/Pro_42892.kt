
data class Node(
    val id: Int,
    val x: Int,
    val y: Int
)
class Solution {
    var n = 0
    lateinit var root : Node
    lateinit var graph : HashMap<Node, Array<Node?>>

    val prefix = mutableListOf<Int>()
    val postfix = mutableListOf<Int>()

    fun solution(nodeinfo: Array<IntArray>): Array<IntArray> {
        n = nodeinfo.size
        graph = hashMapOf()

        val list = arrayListOf<Node>()
        nodeinfo.forEachIndexed { index, info ->
            list.add(Node(index + 1, info[0], info[1]))
        }
        list.sortByDescending { it.y }

        root = list[0]
        graph[root] = arrayOf(null, null)
        for (i in 1 .. list.lastIndex) {
            graph[list[i]] = arrayOf(null, null)
            add(root, list[i])
        }

        preorder(root)
        postorder(root)

        return arrayOf(
            prefix.toIntArray(),
            postfix.toIntArray()
        )
    }

    fun add(parent: Node, node: Node) {
        if (parent.x > node.x) {
            if (graph[parent]!![0] == null) graph[parent]!![0] = node
            else add(graph[parent]!![0]!!, node)
        }
        else {
            if (graph[parent]!![1] == null) graph[parent]!![1] = node
            else add(graph[parent]!![1]!!, node)
        }
    }

    fun preorder(from: Node?) {
        from ?: return

        prefix.add(from.id)
        preorder(graph[from]!![0])
        preorder(graph[from]!![1])
    }

    fun postorder(from: Node?) {
        from ?: return

        postorder(graph[from]!![0])
        postorder(graph[from]!![1])
        postfix.add(from.id)
    }
}

fun main() {
    val s = Solution()
    val nodeinfo = arrayOf(
        intArrayOf(5,3),
        intArrayOf(11,5),
        intArrayOf(13,3),
        intArrayOf(3,5),
        intArrayOf(6,1),
        intArrayOf(1,3),
        intArrayOf(8,6),
        intArrayOf(7,2),
        intArrayOf(2,2)
    )

    println(s.solution(nodeinfo).contentToString())
}