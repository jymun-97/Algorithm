import java.util.*

var n = 0
val stack = LinkedList<Pair<Int, Int>>()
lateinit var nums: IntArray
lateinit var result: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine().split(" ").map { it.toInt() }.toIntArray()
    result = IntArray(n) { -1 }
}

fun solve() {
    var idx = 0
    while (idx < n) {
        val num = nums[idx]
        when {
            stack.isEmpty() -> stack.add(idx++ to num)

            stack.peekLast().second < num -> result[stack.pollLast().first] = num

            else -> stack.add(idx++ to num)
        }
    }
    println(result.joinToString(" "))
}

fun main() {
    input()
    solve()
}