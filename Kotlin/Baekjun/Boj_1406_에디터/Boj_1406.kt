import java.util.*

data class Node(
    val value: Char,
    var pre: Node? = null,
    var next: Node? = null,
)

var n = 0
var m = 0
val start = Node(' ')
lateinit var pnt: Node
lateinit var commands: List<String>

fun input() = with(System.`in`.bufferedReader()) {
    val str = readLine().also { n = it.length }
    var pre = Node(str[0], start).also { start.next = it }
    for (i in 1 until str.length) {
        pre = Node(str[i], pre).also { pre.next = it }
    }
    pnt = pre

    m = readLine().toInt()
    commands = List(m) { readLine() }
}

fun solve() {
    commands.forEach { command ->
        when (command[0]) {
            'L' -> pnt = pnt.pre ?: pnt

            'D' -> pnt = pnt.next ?: pnt

            'B' -> {
                if (pnt == start) return@forEach
                pnt.pre!!.next = pnt.next
                pnt.next?.pre = pnt.pre
                pnt = pnt.pre!!
            }
            'P' -> {
                val newNode = Node(command[2], pnt, pnt.next).also {
                    pnt.next?.pre = it
                    pnt.next = it
                }
                pnt = newNode
            }
        }
    }
    printResult()
}

fun moveToFirst() {
    while (pnt != start && pnt.pre != start) {
        pnt = pnt.pre!!
    }
}

fun printResult() {
    moveToFirst()
    println(
        buildString {
            while (pnt.next != null) {
                append(pnt.value)
                pnt = pnt.next!!
            }
            append(pnt.value)
        }
    )
}

fun main() {
    input()
    solve()
}