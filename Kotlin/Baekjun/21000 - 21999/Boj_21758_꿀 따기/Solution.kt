
var n = 0
lateinit var nums: IntArray
lateinit var left: IntArray
lateinit var right: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine().split(" ").map { it.toInt() }.toIntArray()
    left = IntArray(n)
    right = IntArray(n)

    for (i in 1 until n) {
        left[i] += left[i - 1] + nums[i - 1]
    }
    for (i in n - 2 downTo 0) {
        right[i] += right[i + 1] + nums[i + 1]
    }
}

fun solve() {
    var max = 0
    for (i in 1 until n) {
        max = maxOf(max, right[0] + right[i] - nums[i])
    }
    for (i in 0 until n - 1) {
        max = maxOf(max, left[n - 1] + left[i] - nums[i])
    }
    for (i in 0 until n) {
        max = maxOf(max, right[0] - right[i] + left[n - 1] - left[i])
    }

    println(max)
}

fun main() {
    input()
    solve()
}