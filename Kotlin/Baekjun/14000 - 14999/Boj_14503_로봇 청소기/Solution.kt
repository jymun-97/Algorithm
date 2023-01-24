import java.util.LinkedList

enum class Directions(
    val dir: IntArray,
    val index: Int,
) {
    UP(intArrayOf(-1, 0), 0),
    RIGHT(intArrayOf(0, 1), 1),
    DOWN(intArrayOf(1, 0), 2),
    LEFT(intArrayOf(0, -1), 3);

    fun next(): Directions = if (index == 0) LEFT else Directions[index - 1]

    operator fun get(i: Int) = dir[i]

    companion object {
        operator fun get(index: Int) = Directions.values()[index]
    }
}

var n = 0
var m = 0
var sr = 0
var sc = 0
var sd = 0
lateinit var board: Array<IntArray>
lateinit var visit: Array<BooleanArray>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    readLine().split(" ").map { it.toInt() }.let {
        sr = it[0]
        sc = it[1]
        sd = it[2]
    }
    visit = Array(n) { BooleanArray(m) }
    board = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }
}

fun solve() {
    val que = LinkedList<Int>()
    que.add(sr)
    que.add(sc)
    que.add(sd)
    visit[sr][sc] = true

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()
        val dir = Directions[que.poll()]

        val (nr, nc, nd) = moved(row, col, dir) ?: break

        que.add(nr)
        que.add(nc)
        que.add(nd)

        visit[nr][nc] = true
    }

    println(
        visit.sumOf { it.count { it } }
    )
}

fun moved(row: Int, col: Int, dir: Directions): IntArray? {
    var d = dir.next()
    repeat(4) {
        val nr = row + d[0]
        val nc = col + d[1]

        if (visit[nr][nc] || board[nr][nc] == 1) {
            d = d.next()
            return@repeat
        }

        return intArrayOf(nr, nc, d.index)
    }

    val nr = row - dir[0]
    val nc = col - dir[1]

    return if (board[nr][nc] == 1) null else intArrayOf(nr, nc, dir.index)
}

fun main() {
    input()
    solve()
}