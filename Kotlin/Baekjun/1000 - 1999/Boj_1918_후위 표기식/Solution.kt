import java.util.LinkedList

sealed class Operator(
    val text: Char,
    val priority: Int
) {
    object Add : Operator('+', 1)
    object Mul : Operator('*', 2)
    object Div : Operator('/', 2)
    object Sub : Operator('-', 1)
    object Open : Operator('(', 0)
    object Close : Operator(')', 0)

    operator fun compareTo(other: Operator): Int = priority.compareTo(other.priority)

    companion object {
        fun of(oper: Char) = when (oper) {
            '+' -> Add

            '-' -> Sub

            '*' -> Mul

            '/' -> Div

            '(' -> Open

            else -> Close
        }
    }
}

val stack = LinkedList<Char>()
val operStack = LinkedList<Operator>()
var str = ""

fun input() = with(System.`in`.bufferedReader()) {
    str = readLine()
}

fun solve() {
    str.forEach {
        if (!isOperator(it)) {
            stack.add(it)
            return@forEach
        }
        val oper = Operator.of(it)
        when {
            operStack.isEmpty() -> operStack.add(oper)

            oper is Operator.Open -> operStack.add(oper)

            oper is Operator.Close -> {
                while (operStack.peekLast() !is Operator.Open) {
                    stack.add(operStack.pollLast().text)
                }
                operStack.pollLast()
            }

            operStack.peekLast() < Operator.of(it) -> operStack.add(Operator.of(it))

            else -> {
                while (operStack.isNotEmpty() && operStack.peekLast() >= oper) {
                    stack.add(operStack.pollLast().text)
                }
                operStack.add(oper)
            }
        }
    }

    while (operStack.isNotEmpty()) {
        stack.add(operStack.pollLast().text)
    }

    println(
        stack.joinToString("")
    )
}

fun isOperator(c: Char) = hashSetOf('+', '-', '*', '/', '(', ')').contains(c)

fun main() {
    input()
    solve()
}