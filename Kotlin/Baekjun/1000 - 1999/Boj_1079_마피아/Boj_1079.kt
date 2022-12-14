import kotlin.math.max

var n = 0
var mafia = 0
var ans = 0
lateinit var scores: IntArray
lateinit var isDead: BooleanArray
lateinit var amount: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    scores = readLine().split(" ").map { it.toInt() }.toIntArray()
    isDead = BooleanArray(n)
    amount = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
    mafia = readLine().toInt()
}

fun solve() {
    dfs(0, n)
    println(ans)
}

fun dfs(count: Int, remain: Int): Unit = when {
    isDead[mafia] || remain <= 1 -> ans = max(ans, count)

    remain % 2 == 1 -> findTarget().let { target ->
        isDead[target] = true
        dfs(count, remain - 1)
        isDead[target] = false
    }

    else -> (0 until n).forEach { target ->
        if (target == mafia || isDead[target]) return@forEach

        isDead[target] = true
        (0 until n).forEach { scores[it] += amount[target][it] }
        dfs(count + 1, remain - 1)
        (0 until n).forEach { scores[it] -= amount[target][it] }
        isDead[target] = false
    }
}

fun findTarget(): Int {
    var maxScore = 0
    var target = -1
    scores.forEachIndexed { id, score ->
        if (maxScore < score && !isDead[id]) {
            maxScore = score
            target = id
        }
    }
    return target
}

fun main() {
    input()
    solve()
}