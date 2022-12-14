import java.util.*

lateinit var nums: List<Int>
lateinit var opers: List<String>

fun input() = with(System.`in`.bufferedReader()) {
    val line = readLine()
    nums = line.split("+", "-").map { it.toInt() }
    opers = line.split("\\d+".toRegex()).filter { it.isNotEmpty() }
}

fun solve() {
    val numStack = LinkedList<Int>()
    val operStack = LinkedList<String>()

    numStack.add(nums[0])
    for (i in opers.indices) {
        val num = nums[i + 1]
        val oper = opers[i]

        when (oper) {
            "+" -> numStack.add(
                numStack.pollLast() + num
            )
            "-" -> {
                numStack.add(num)
                operStack.add(oper)
            }
        }
    }

    var ans = numStack.poll()
    while (numStack.isNotEmpty()) {
        ans -= numStack.poll()
    }
    println(ans)
}

fun main() {
    input()
    solve()
}