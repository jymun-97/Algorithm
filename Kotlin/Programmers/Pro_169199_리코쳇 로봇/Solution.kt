import java.util.LinkedList

class Solution {
    var n = 0
    var m = 0
    var sr = 0
    var sc = 0
    var destR = 0
    var destC = 0
    lateinit var board: Array<CharArray>
    lateinit var visit: Array<Array<BooleanArray>>

    val dir = arrayOf(
        intArrayOf(1, 0),
        intArrayOf(-1, 0),
        intArrayOf(0, 1),
        intArrayOf(0, -1)
    )

    fun solution(board: Array<String>): Int {
        n = board.size
        m = board[0].length
        this.board = board.map { it.toCharArray() }.toTypedArray()
        visit = Array(n) { Array(m) { BooleanArray(4) } }

        for (i in 0 until n) {
            for (j in 0 until m) {
                when (board[i][j]) {
                    'R' -> {
                        sr = i
                        sc = j
                    }
                    'G' -> {
                        destR = i
                        destC = j
                    }
                }
            }
        }

        return bfs()
    }

    fun bfs(): Int {
        val que = LinkedList<Int>().apply {
            add(sr)
            add(sc)
            add(0)
        }
        visit[sr][sc].fill(true)

        while (que.isNotEmpty()) {
            val row = que.poll()
            val col = que.poll()
            val dist = que.poll()

            if (row == destR && col == destC) return dist

            dir.forEachIndexed { i, it ->
                var nr = row + it[0]
                var nc = col + it[1]

                while (nr in 0 until n && nc in 0 until m && board[nr][nc] != 'D') {
                    nr += it[0]
                    nc += it[1]
                }

                nr -= it[0]
                nc -= it[1]

                if (!visit[nr][nc][i]) que.apply {
                    add(nr)
                    add(nc)
                    add(dist + 1)

                    visit[nr][nc][i] = true
                }
            }
        }

        return -1
    }
}