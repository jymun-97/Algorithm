import java.io.*
import java.util.*

var ans = 0
var flag = false
var count = 0

const val r = 12
const val c = 6

val map = Array<StringBuilder>(r + 1) { StringBuilder() }

val dir = arrayOf(
    arrayOf(1, 0),
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(0, -1)
)

fun input() = with(System.`in`.bufferedReader()) {
    for (row in 1..r) {
        map[row].append(' ').append(readLine())

        for (col in 1..c) {
            if (map[row][col] != '.') count++
        }
    }
}

fun solve() {
    while (!flag) {
        remove()
        gravity()
        ans++
    }
    println(ans - 1)
}

fun gravity() {
    val dest = IntArray(c + 1) { r }
    for (row in r downTo 1) {
        for (col in 1 .. c) {
            if (map[row][col] != '.') {
                map[dest[col]][col] = map[row][col]
                if (dest[col] != row) map[row][col] = '.'
                dest[col]--
            }
        }
    }
}

fun remove() {
    val visit = Array(r + 1) { BooleanArray(c + 1) }
    val temp = count
    for (i in r downTo 1) {
        for (j in 1..c) {
            if (visit[i][j] || map[i][j] == '.') continue
            bfs(i, j, visit)
        }
    }
    flag = temp == count
}

fun bfs(sr: Int, sc: Int, visit: Array<BooleanArray>) {
    var target = map[sr][sc]
    val set = LinkedList<Int>()
    set.add(sr)
    set.add(sc)

    val que = LinkedList<Int>()
    que.add(sr)
    que.add(sc)
    visit[sr][sc] = true

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        for (i in 0 until 4) {
            val nr = row + dir[i][0]
            val nc = col + dir[i][1]

            if (nr < 1 || nr > r || nc < 1 || nc > c) continue
            if (visit[nr][nc] || map[nr][nc] != target) continue

            set.add(nr)
            set.add(nc)

            que.add(nr)
            que.add(nc)
            visit[nr][nc] = true
        }
    }

    if (set.size / 2 >= 4) {
        while (set.isNotEmpty()) {
            val row = set.poll()
            val col = set.poll()

            map[row][col] = '.'
            count--
        }
    }
}

fun printMap() {
    for (i in 1..r) {
        for (j in 1..c) {
            print(map[i][j])
        }
        println()
    }
    println()
}

fun main() {
    input()
    solve()
}