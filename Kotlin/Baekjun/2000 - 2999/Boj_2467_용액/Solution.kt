import kotlin.math.absoluteValue

val ans = intArrayOf(0, 0)
var n = 0
lateinit var nums: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine().split(" ").map { it.toInt() }.sorted().toIntArray()
}

fun solve() {
    var left = 0
    var right = n - 1
    var min = Int.MAX_VALUE

    while (left < right) {
        val sum = nums[left] + nums[right]
        if (min > sum.absoluteValue) {
            updateAns(left, right)
            min = sum.absoluteValue
        }

        when {
            sum < 0 -> left++

            sum > 0 -> right--

            else -> {
                updateAns(left, right)
                break
            }
        }
    }

    println("${ans[0]} ${ans[1]}")
}

fun updateAns(left: Int, right: Int) = ans.let {
    it[0] = nums[left]
    it[1] = nums[right]
}

fun main() {
    input()
    solve()
}