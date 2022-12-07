import java.util.*

val nums = arrayListOf<Int>()
lateinit var inputNumbers: List<Int>

fun input() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    inputNumbers = List(n) { readLine().toInt() }
}

fun solve() {
    val builder = StringBuilder()
    val left = PriorityQueue<Int>() { i1, i2 -> i2 - i1 }
    val right = PriorityQueue<Int>()

    inputNumbers.forEach {
        when {
            left.size <= right.size -> left.add(it)

            else -> right.add(it)
        }
        if (left.isNotEmpty() && right.isNotEmpty() && left.peek() > right.peek()) {
            left.add(right.poll()).also {
                right.add(left.poll())
            }
        }
        builder.append(left.peek()).append('\n')
    }
    println(builder)
}

fun main() {
    input()
    solve()
}