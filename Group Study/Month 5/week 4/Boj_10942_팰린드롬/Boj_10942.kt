import java.io.*
import java.util.*

var n = 0
var m = 0

lateinit var nums : IntArray
lateinit var commands: LinkedList<Int>
lateinit var dp: Array<BooleanArray>
lateinit var visit: Array<BooleanArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nums = IntArray(n + 1)
    dp = Array(n + 1) { BooleanArray(n + 1) }
    visit = Array(n + 1) { BooleanArray(n + 1) }

    StringTokenizer(readLine()).apply {
        for (i in 1 .. n) nums[i] = nextToken().toInt()
    }

    m = readLine().toInt()
    commands = LinkedList()
    for (i in 0 until m) {
        StringTokenizer(readLine()).apply {
            commands.add(nextToken().toInt())
            commands.add(nextToken().toInt())
        }
    }
}

fun solve() {
    init()

    val sb = StringBuilder()
    while (commands.isNotEmpty()) {
        val start = commands.poll()
        val end = commands.poll()

        sb.append(
            if (isPalindrome(start, end)) 1
            else 0
        ).append('\n')
    }
    println(sb)
}

fun init() {
    for (i in 1 until n) {
        dp[i][i] = true
        visit[i][i] = true

        dp[i][i + 1] = nums[i] == nums[i + 1]
        visit[i][i + 1] = true
    }
    dp[n][n] = true
    visit[n][n] = true
}

fun isPalindrome(start: Int, end: Int): Boolean {
    if (visit[start][end]) return dp[start][end]

    visit[start][end] = true
    dp[start][end] = (nums[start] == nums[end]) and isPalindrome(start + 1, end - 1)

    return dp[start][end]
}

fun main() {
    input()
    solve()
}