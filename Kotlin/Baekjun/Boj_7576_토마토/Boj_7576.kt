import java.util.*

var n = 0
var m = 0
var remain = 0
val que = LinkedList<Int>()
lateinit var board: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        m = nextToken().toInt()
        n = nextToken().toInt()
    }
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
}

fun solve() {
    init()

    var ans = 0
    while (que.isNotEmpty()) {
        spread()
        ans++
    }
    println(if (remain > 0) -1 else ans - 1)
}

fun init() {
    for (i in 0 until n) {
        for (j in 0 until m) {
            when (board[i][j]) {
                0 -> remain++

                1 -> que.apply {
                    add(i)
                    add(j)
                }
            }
        }
    }
}

fun spread() {
    val temp = LinkedList<Int>()
    val dir = arrayOf(
        arrayOf(1, 0),
        arrayOf(-1, 0),
        arrayOf(0, 1),
        arrayOf(0, -1)
    )
    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until m) return@forEach
            if (board[nr][nc] != 0) return@forEach

            temp.add(nr)
            temp.add(nc)
            board[nr][nc] = 1
            remain--
        }
    }
    que.addAll(temp)
}

fun main() {
    input()
    solve()
}