import java.lang.Integer.max

var n = 0
var ans = 0
lateinit var board: Array<IntArray>
lateinit var selected: IntArray

const val UP = 0
const val DOWN = 1
const val LEFT = 2
const val RIGHT = 3

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n) { IntArray(n) }
    selected = IntArray(5)

    for (i in 0 until n) {
        board[i] = readLine().split(' ').map { it.toInt() }.toIntArray()
    }
}

fun solve() {
    dfs(0)
    println(ans)
}

fun dfs(k: Int) {
    if (k == 5) {
        run()
        return
    }

    for (i in 0 until 4) {
        selected[k] = i
        dfs(k + 1)
    }
}

fun run() {
    val board = copy()

    selected.forEach { dir ->
        move(board, dir)
    }

    var cand = 0
    board.forEach { cand = max(cand, it.maxOrNull()!!) }

    ans = max(ans, cand)
}

fun move(board: Array<IntArray>, dir: Int) {
    when (dir) {
        UP -> moveUp(board)

        DOWN -> moveDown(board)

        LEFT -> moveLeft(board)

        RIGHT -> moveRight(board)
    }
}

fun moveUp(board: Array<IntArray>) {
    for (i in 0 until n) {
        for (j in 1 until n) {
            if (board[j][i] != 0) {
                var target = j - 1
                while (target >= 0 && board[target][i] == 0) target--

                if (target == j - 1) continue

                board[target + 1][i] = board[j][i]
                board[j][i] = 0
            }
        }
    }
    for (i in 0 until n) {
        for (j in 1 until n) {
            if (board[j][i] == board[j - 1][i]) {
                board[j - 1][i] *= 2
                board[j][i] = 0
            }
        }
    }
    for (i in 0 until n) {
        for (j in 1 until n) {
            if (board[j][i] != 0) {
                var target = j - 1
                while (board[target][i] == 0) target--

                if (target == j - 1) continue

                board[target + 1][i] = board[j][i]
                board[j][i] = 0
            }
        }
    }
}

fun moveDown(board: Array<IntArray>) {
    for (i in 0 until n) {
        for (j in n - 2 downTo 0) {
            if (board[j][i] != 0) {
                var target = j + 1
                while (target < n && board[target][i] == 0) target++

                if (target == j + 1) continue

                board[target - 1][i] = board[j][i]
                board[j][i] = 0
            }
        }
    }
    for (i in 0 until n) {
        for (j in n - 2 downTo 0) {
            if (board[j][i] == board[j + 1][i]) {
                board[j + 1][i] *= 2
                board[j][i] = 0
            }
        }
    }
    for (i in 0 until n) {
        for (j in n - 2 downTo 0) {
            if (board[j][i] != 0) {
                var target = j + 1
                while (board[target][i] == 0) target++

                if (target == j + 1) continue

                board[target - 1][i] = board[j][i]
                board[j][i] = 0
            }
        }
    }
}

fun moveLeft(board: Array<IntArray>) {
    for (i in 0 until n) {
        for (j in 1 until n) {
            if (board[i][j] != 0) {
                var target = j - 1
                while (target >= 0 && board[i][target] == 0) target--

                if (target == j - 1) continue

                board[i][target + 1] = board[i][j]
                board[i][j] = 0
            }
        }
    }
    for (i in 0 until n) {
        for (j in 1 until n) {
            if (board[i][j] == board[i][j - 1]) {
                board[i][j - 1] *= 2
                board[i][j] = 0
            }
        }
    }
    for (i in 0 until n) {
        for (j in 1 until n) {
            if (board[i][j] != 0) {
                var target = j - 1
                while (target >= 0 && board[i][target] == 0) target--

                if (target == j - 1) continue

                board[i][target + 1] = board[i][j]
                board[i][j] = 0
            }
        }
    }
}

fun moveRight(board: Array<IntArray>) {
    for (i in 0 until n) {
        for (j in n - 2 downTo 0) {
            if (board[i][j] != 0) {
                var target = j + 1
                while (target < n && board[i][target] == 0) target++

                if (target == j + 1) continue

                board[i][target - 1] = board[i][j]
                board[i][j] = 0
            }
        }
    }
    for (i in 0 until n) {
        for (j in n - 2 downTo 0) {
            if (board[i][j] == board[i][j + 1]) {
                board[i][j + 1] *= 2
                board[i][j] = 0
            }
        }
    }
    for (i in 0 until n) {
        for (j in n - 2 downTo 0) {
            if (board[i][j] != 0) {
                var target = j + 1
                while (target < n && board[i][target] == 0) target++

                if (target == j + 1) continue

                board[i][target - 1] = board[i][j]
                board[i][j] = 0
            }
        }
    }
}

fun copy(): Array<IntArray> {
    val newBoard = board.clone()
    for (i in 0 until n) newBoard[i] = board[i].clone()

    return newBoard
}

fun main() {
    input()
    solve()
}