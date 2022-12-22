
var n = 0
var white = 0
var blue = 0
lateinit var board: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
}

fun solve() {
    dfs(0, 0, n)
    println(
        buildString {
            appendLine(white)
            append(blue)
        }
    )
}

fun dfs(sr: Int, sc: Int, size: Int) {
    if (size == 1) {
        if (board[sr][sc] == 0) white++
        else blue++

        return
    }

    val color = board[sr][sc]
    for (row in sr until sr + size) {
        for (col in sc until sc + size) {
            if (color == board[row][col]) continue

            dfs(sr, sc, size / 2)
            dfs(sr + size / 2, sc, size / 2)
            dfs(sr, sc + size / 2, size / 2)
            dfs(sr + size / 2, sc + size / 2, size / 2)

            return
        }
    }
    if (color == 0) white++ else blue++
}

fun main() {
    input()
    solve()
}