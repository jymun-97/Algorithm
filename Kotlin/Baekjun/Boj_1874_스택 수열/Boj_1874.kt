import java.util.*

var n = 0
var value = 1
val sb = StringBuilder()
val stack = LinkedList<Int>()
lateinit var nums: List<Int>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = List(n) { readLine().toInt() }
}

fun solve() {
    for (i in 0 until n) {
        val target = nums[i]
        if (!make(target)) {
            println("NO")
            return
        }
    }
    println(sb)
}

fun make(target: Int): Boolean = when {
    value <= target -> {
        for (i in value .. target) {
            stack.addLast(i)
            sb.append('+').append('\n')
        }
        value = stack.pollLast() + 1
        sb.append('-').append('\n')
        true
    }

    stack.isEmpty() || !stack.contains(target) -> false

    else -> {
        while (stack.peekLast() != target) {
            stack.pollLast()
            sb.append('-').append('\n')
        }
        stack.pollLast()
        sb.append('-').append('\n')
        true
    }
}

fun main() {
    input()
    solve()
}