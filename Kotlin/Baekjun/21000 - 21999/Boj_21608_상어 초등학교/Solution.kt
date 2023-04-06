
var n = 0
var ans = 0
lateinit var board: Array<IntArray>
lateinit var graph: Array<HashSet<Int>>
lateinit var order: MutableList<Int>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n + 1) { IntArray(n + 1) }
    graph = Array(n * n + 1) { hashSetOf() }
    order = mutableListOf()

    repeat(n * n) {
        readLine().split(" ").map { it.toInt() }.let {
            val from = it[0]

            graph[from].addAll(it.drop(1))
            order.add(from)
        }
    }
}

fun solve() {
    order.forEach { id ->
        seat(id)
    }

    for (row in 1 .. n) {
        for (col in 1..n) {
            val from = board[row][col]
            ans += when (getScore(row, col, from).first) {
                1 -> 1
                2 -> 10
                3 -> 100
                4 -> 1000
                else -> 0
            }
        }
    }

    println(ans)
}

fun seat(id: Int) {
    var target = 0 to 0
    var max = -1 to -1

    for (row in 1 .. n) {
        for (col in 1 .. n) {
            if (board[row][col] != 0) continue

            val score = getScore(row, col, id)
            when {
                max.first < score.first -> {
                    target = row to col
                    max = score
                }
                max.first == score.first && max.second < score.second -> {
                    target = row to col
                    max = score
                }
            }
        }
    }

    board[target.first][target.second] = id
}

fun getScore(row: Int, col: Int, from: Int): Pair<Int, Int> {
    var nLike = 0
    var nEmpty = 0
    val dir = arrayOf(
        arrayOf(1, 0),
        arrayOf(-1, 0),
        arrayOf(0, 1),
        arrayOf(0, -1)
    )
    dir.forEach {
        val nr = row + it[0]
        val nc = col + it[1]

        if (nr !in 1 .. n || nc !in 1 .. n) return@forEach

        val to = board[nr][nc]
        when {
            graph[from].contains(to) -> nLike++

            to == 0 -> nEmpty++
        }
    }

    return nLike to nEmpty
}

fun main() {
    input()
    solve()
}