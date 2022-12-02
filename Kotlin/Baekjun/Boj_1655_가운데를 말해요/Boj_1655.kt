import java.util.*
import kotlin.math.min

val builder = StringBuilder()

var n = 0
lateinit var root: Node
lateinit var values: IntArray

data class Node(
    var parent: Node? = null,
    var left: Node? = null,
    var right: Node? = null,
    val value: Int,
    var count: Int = 0,
    var index: Int = 0,
) {
//    val count: Int
//        get() = (left?.count ?: 0) + (right?.count ?: 0)
//
//    val index: Int
//        get() = (left?.count ?: 0) +
//                if (parent != null && parent!!.value <= value) parent!!.index + 1 else 0
}

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    values = IntArray(n) { readLine().toInt() }
}

fun solve() {
    root = Node(value = values[0])
//    builder.append(root.value).append('\n')

    for (i in 1 until values.size) {
        insert(root, values[i])

//        val target = (i + 1) / 2
//        builder.append(
//            if (i % 2 == 0) find(root, target).value
//            else min(
//                find(root, target).value,
//                find(root, target - 1).value
//            )
//        ).append('\n')
    }
    val ans = find(root, 0)
    println(ans)
}

fun insert(parent: Node, value: Int): Unit = when {
    value <= parent.value -> {
        parent.count++
        parent.index++

        parent.left?.let {
            insert(it, value)
        } ?: run {
            parent.left = Node(parent = parent, value = value)
        }
    }
    else -> {
        parent.count++

        parent.right?.let {
            insert(it, value)
        } ?: run {
            parent.right = Node(parent = parent, value = value, index = parent.index + 1)
        }
    }
}

fun find(node: Node, target: Int): Node = when {
    node.index == target -> node

    node.index > target -> find(node.left!!, target)

    else -> find(node.right!!, target)
}

fun main() {
    input()
    solve()
}