import kotlin.math.max

var n = 0
var ans = 0
lateinit var nums: List<Int>
lateinit var visit: BooleanArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = readLine().split(" ").map { it.toInt() }
    visit = BooleanArray(n)
}

fun solve() {
    (0 until n).forEach { dfs(it, 1) }
    println(ans)
}

fun dfs(from: Int, length: Int) {
    visit[from] = true
    if (ans >= length + n - from - 1) return

    for (to in from + 1 until n) {
        if (nums[from] < nums[to] && !visit[to]) {
            ans = max(ans, length + 1)
            dfs(to, length + 1)
        }
    }
}

fun main() {
    input()
    solve()
}