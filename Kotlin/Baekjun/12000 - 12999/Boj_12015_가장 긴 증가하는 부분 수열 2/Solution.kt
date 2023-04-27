
var n = 0
val result = mutableListOf<Int>()
lateinit var nums: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine().split(" ").map { it.toInt() }.toIntArray()
}

fun solve() {
    result.add(nums[0])
    for (i in 1 until n) when {
        nums[i] > result.last() -> result.add(nums[i])

        nums[i] == result.last() -> continue

        else -> {
            val target = lowerBound(nums[i])
            result[target] = nums[i]
        }
    }

    println(result.size)
}

fun lowerBound(num: Int): Int {
    var left = 0
    var right = result.lastIndex
    var target = -1

    while (left <= right) {
        val mid = (left + right) / 2

        when {
            result[mid] == num -> return mid

            result[mid] < num -> left = mid + 1

            else -> {
                right = mid - 1
                target = mid
            }
        }
    }

    return target
}

fun main() {
    input()
    solve()
}