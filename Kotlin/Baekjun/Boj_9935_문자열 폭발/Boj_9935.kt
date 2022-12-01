import java.util.LinkedList

var str = ""
var target = ""

fun input() = with(System.`in`.bufferedReader()) {
    str = readLine()
    target = readLine()
}

fun solve() {
    val stack = LinkedList<Char>()
    val length = target.length

    str.forEach { c ->
        stack.push(c)

        val temp = LinkedList<Char>()
        for (i in target.lastIndex downTo 0) {
            if (stack.isEmpty() || stack.peek() != target[i]) break
            else {
                temp.push(stack.poll())
            }
        }

        if (temp.size < length) while (temp.isNotEmpty()) {
            stack.addFirst(temp.poll())
        }
    }

    println(stack.reversed().joinToString("").ifEmpty { "FRULA" })
}

fun main() {
    input()
    solve()
}