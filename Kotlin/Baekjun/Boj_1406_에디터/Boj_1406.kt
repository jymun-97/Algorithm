import java.util.*

var pointer = 0
val editor = LinkedList<Char>()
lateinit var commands: List<String>

fun input() = with(System.`in`.bufferedReader()) {
    editor.addAll(readLine().toCharArray().toList())
    pointer = editor.size

    val size = readLine().toInt()
    commands = List(size) { readLine() }
}

fun solve() {
    commands.forEach { command ->
        when (command[0]) {
            'L' -> if (pointer > 0) pointer--

            'D' -> if (pointer < editor.size) pointer++

            'B' -> if (pointer > 0) editor.removeAt(--pointer)

            'P' -> editor.add(pointer++, command[2])
        }
    }

    println(editor.joinToString(""))
}

fun main() {
    input()
    solve()
}