import java.util.*

var n = 0
var r = 0
var c = 0
var size = 2
var ans = 0
var remain = 0
var count = 0
val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)
lateinit var board: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    board = Array(n) { row ->
        readLine().split(" ").mapIndexed { col, s ->
            val value = s.toInt()
            if (value == 9) {
                r = row
                c = col
                0
            }
            else {
                if (value > 0) remain++
                value
            }
        }.toIntArray()
    }
}

fun solve() {
    while (find()) {}
    println(ans)
}

fun find(): Boolean {
    if (remain == 0) return false

    val visit = Array(n) { BooleanArray(n) }
    val que = LinkedList<Int>().apply {
        add(r)
        add(c)
        add(0)
        visit[r][c] = true
    }
    var min = Int.MAX_VALUE
    val cands = arrayListOf<Pair<Int, Int>>()

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        val d = que.poll()

        if (d > min) continue
        if (isValid(row, col)) {
            min = d
            cands.add(row to col)
            continue
        }

        dir.forEach {
            val nr = row + it[0]
            val nc = col + it[1]

            if (nr !in 0 until n || nc !in 0 until n) return@forEach
            if (visit[nr][nc] || board[nr][nc] > size) return@forEach

            que.apply {
                add(nr)
                add(nc)
                add(d + 1)
                visit[nr][nc] = true
            }
        }
    }
    return if (cands.isEmpty()) false else {
        val target = cands.sortedWith(compareBy( { it.first }, { it.second }))[0]
        board[target.first][target.second] = 0
        r = target.first
        c = target.second
        ans += min
        remain--
        if (++count == size) {
            count = 0
            size++
        }
        true
    }
}

fun isValid(row: Int, col: Int) = board[row][col] in 1..6 && board[row][col] < size

fun main() {
    input()
    solve()
}