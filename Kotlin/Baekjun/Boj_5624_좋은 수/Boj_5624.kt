import java.util.*

var n = 0
val visit = hashSetOf<Int>()
lateinit var nums: List<Int>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine().split(" ").map { it.toInt() }
}

fun solve() {
    var ans = 0
    visit.add(nums[0] * 2)
    for (i in 1 until n) {
        for (j in 0 until i) {
            val target = nums[i] - nums[j]
            if (visit.contains(target)) {
                ans++
                break
            }
        }
        for (j in 0 .. i) {
            visit.add(nums[i] + nums[j])
        }
    }
    println(ans)
}

fun main() {
    input()
    solve()
}