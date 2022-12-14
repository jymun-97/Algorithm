
var n = 0
lateinit var nums : List<Int>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine()
        .split(" ")
        .map { it.toInt() }
        .sorted()
}

fun solve() {
    var ans = 0

    nums.forEachIndexed { i, target ->
        var left = 0
        var right = n - 1

        while (left < right) {
            val sum = nums[left] + nums[right]
            when {
                left == i -> left++

                right == i -> right--

                sum < target -> left++

                sum > target -> right--

                else -> {
                    ans++
                    return@forEachIndexed
                }
            }
        }
    }

    println(ans)
}

fun main() {
    input()
    solve()
}