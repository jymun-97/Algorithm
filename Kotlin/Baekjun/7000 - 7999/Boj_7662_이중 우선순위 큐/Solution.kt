import java.util.*


val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

var k = 0
var flag = false
val commands = mutableListOf<Pair<String, Int>>()
lateinit var que: LinkedList<Int>

fun input() = with(reader) {
    k = readLine().toInt()
    flag = false
    que = LinkedList<Int>()

    repeat(k) {
        val tokens = readLine().split(" ")
        commands.add(
            tokens[0] to tokens[1].toInt()
        )
    }
}

fun solve() {
    commands.forEach {
        val (command, value) = it

        when (command) {
            "I" -> insert(value)

            "D" -> delete(value != 1)
        }
    }
    if (que.isEmpty()) builder.appendLine("EMPTY")
    else {
        que.sort()
        builder.append(que.last).append(' ').appendLine(que.first)
    }
}

fun insert(value: Int) {
    if (que.isNotEmpty() && value < que.peekLast()) flag = true
    que.add(value)
}

fun delete(isTargetMin: Boolean) {
    if (que.isEmpty()) return

    if (flag) {
        que.sort()
        flag = false
    }
    if (isTargetMin) que.poll() else que.pollLast()
}

fun main() = with(reader) {
    repeat(readLine().toInt()) {
        input()
        solve()
    }
    println(builder)
}