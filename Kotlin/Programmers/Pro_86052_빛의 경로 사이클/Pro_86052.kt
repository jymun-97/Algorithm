import java.util.LinkedList

class Solution {
    var n = 0
    var m = 0
    lateinit var map : Array<StringBuilder>
    lateinit var visit : Array<Array<IntArray>>

    val direction = arrayOf(
        arrayOf(1, 0),
        arrayOf(-1, 0),
        arrayOf(0, 1),
        arrayOf(0, -1)
    )

    fun solution(grid: Array<String>): IntArray {
        n = grid.size
        m = grid[0].length

        map = Array(n + 1) { StringBuilder() }
        visit = Array(4) { Array(n + 1) { IntArray(m + 1) } }

        for (i in 1 .. n) {
            map[i].append(' ').append(grid[i - 1])
        }

        val list = arrayListOf<Int>()
        for (dir in 0 until 4) {
            for (row in 1..n) {
                for (col in 1..m) {
                    if (visit[dir][row][col] > 0) continue

                    list.add(bfs(row, col, dir))
                }
            }
        }

        val answer = IntArray(list.size)
        list.forEachIndexed { index, value -> answer[index] = value }

        return answer.sortedArray()
    }

    fun bfs(sr: Int, sc: Int, sd: Int) : Int {
        val que = LinkedList<Int>()
        que.add(sr)
        que.add(sc)
        que.add(sd)
        que.add(1)
        visit[sd][sr][sc] = 1

        var length = 1
        while (que.isNotEmpty()) {
            val row = que.poll()
            val col = que.poll()
            val dir = que.poll()
            val cnt = que.poll()

            if (visit[dir][row][col] > 1) {
                length = cnt - 1
                break
            }

            val info = getNext(row, col, dir)
            que.add(info[0])
            que.add(info[1])
            que.add(info[2])
            que.add(cnt + 1)

            visit[info[2]][info[0]][info[1]]++
        }

        return length
    }

    fun getNext(row: Int, col: Int, dir: Int): IntArray {
        val nd = getNextDir(map[row][col], dir)

        var nr = row + direction[nd][0]
        var nc = col + direction[nd][1]

        if (nr < 1) nr = n
        if (nr > n) nr = 1
        if (nc < 1) nc = m
        if (nc > m) nc = 1

        return intArrayOf(nr, nc, nd)
    }

    fun getNextDir(value: Char, dir: Int): Int {
        if (value == 'S') return dir

        return when (value) {
            'R' -> {
                when (dir) {
                    0 -> 3
                    1 -> 2
                    2 -> 0
                    else -> 1
                }
            }
            else -> {
                when (dir) {
                    0 -> 2
                    1 -> 3
                    2 -> 1
                    else -> 0
                }
            }
        }
    }
}