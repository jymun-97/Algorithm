
var n = 0
var m = 0
var k = 0
var ans = Int.MAX_VALUE
lateinit var isLinked: Array<BooleanArray>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[2]
        m = it[0]
        k = it[1]
    }
    isLinked = Array(n + 1) { BooleanArray(m + 1) }
    repeat(k) {
        val (row, col) = readLine().split(" ").map { it.toInt() }
        isLinked[row][col] = true
    }
}

fun solve() {
    dfs(1, 1, 0, true)
    println(if (ans == Int.MAX_VALUE) -1 else ans)
}

fun moveDown(row: Int, col: Int, flag: Boolean): Int = when {
    row > n -> col

    !flag && isLinked[row][col - 1] -> moveDown(row, col - 1, true)

    !flag && isLinked[row][col] -> moveDown(row, col + 1, true)

    col > m -> moveDown(row + 1, 1, false)

    else -> moveDown(row + 1, col, false)
}

fun dfs(row: Int, col: Int, count: Int, flag: Boolean) : Unit = when {
    count >= ans || count > 3 -> {}

    flag && isValid() -> ans = count

    row > n -> {}

    col == m -> dfs(row + 1, 1, count, flag)

    isLinked[row][col] || isLinked[row][col + 1] -> dfs(row, col + 1, count, false)

    else -> {
        dfs(row, col + 1, count, false)
        isLinked[row][col] = true
        dfs(row, col + 1, count + 1, true)
        isLinked[row][col] = false
    }
}

fun isValid() = (1..m).all { it == moveDown(1, it, false) }

fun main() {
    input()
    solve()
}