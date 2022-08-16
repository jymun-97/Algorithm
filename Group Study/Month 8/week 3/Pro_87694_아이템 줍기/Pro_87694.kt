import java.util.LinkedList

class Solution {
    data class Point(
        val x: Int,
        val y: Int
    )
    val board = Array(101) { IntArray(101) }
    val set = hashSetOf<Point>()
    val dir = arrayOf(
        arrayOf(1, 0),
        arrayOf(-1, 0),
        arrayOf(0, 1),
        arrayOf(0, -1)
    )

    fun solution(
        rectangle: Array<IntArray>,
        characterX: Int,
        characterY: Int,
        itemX: Int,
        itemY: Int
    ) : Int {
        rectangle.forEach {
            val (leftX, leftY, rightX, rightY) = it.map { it * 2 }
            marking(leftX, leftY, rightX, rightY)
        }
        makeSet()

        val visit = Array(101) { BooleanArray(101) }
        val que = LinkedList<Int>()
        que.add(characterX)
        que.add(characterY)
        que.add(0)
        visit[characterX][characterY] = true

        while (que.isNotEmpty()) {
            val x = que.poll()
            val y = que.poll()
            val dist = que.poll()

            if (x == itemX * 2 && y == itemY * 2) return dist / 2 - 1

            dir.forEach {
                val nx = x + it[0]
                val ny = y + it[1]

                if (set.contains(Point(nx, ny)) && !visit[nx][ny]) {
                    que.add(nx)
                    que.add(ny)
                    que.add(dist + 1)

                    visit[nx][ny] = true
                }
            }
        }

        return -1
    }

    fun marking(leftX: Int, leftY: Int, rightX: Int, rightY: Int) {
        for (i in leftX .. rightX) {
            board[i][leftY]++
            board[i][rightY]++
        }
        for (i in leftY + 1 until rightY) {
            board[leftX][i]++
            board[rightX][i]++
        }
    }

    fun makeSet() {
        val checkDir = arrayOf(
            arrayOf(1, 1),
            arrayOf(-1, 1),
            arrayOf(1, -1),
            arrayOf(-1, -1)
        )
        val visit = hashSetOf<Point>()
        val que = LinkedList<Point>()
        que.add(Point(0, 0))
        visit.add(Point(0, 0))

        while (que.isNotEmpty()) {
            val from = que.poll()
            checkDir.forEach {
                val nx = from.x + it[0]
                val ny = from.y + it[1]

                if (nx !in 0 .. 100 || ny !in 0 .. 100) return@forEach
                if (board[nx][ny] == 0) return@forEach

                set.add(Point(nx, ny))
            }

            dir.forEach {
                val nx = from.x + it[0]
                val ny = from.y + it[1]
                val next = Point(nx, ny)

                if (nx !in 0 .. 100 || ny !in 0 .. 100) return@forEach
                if (visit.contains(next) || board[nx][ny] != 0) return@forEach

                que.add(next)
                visit.add(next)
            }
        }
    }

    fun print() {
        for (i in 0 .. 20) {
            for (j in 0 .. 20) {
                print("${board[i][j]} ")
            }
            println()
        }
        println()
    }
}

fun main() {
    val sol = Solution()
    val rectangle = arrayOf(
        intArrayOf(1, 1, 8, 4),
        intArrayOf(2, 2, 4, 9),
        intArrayOf(3, 6, 9, 8),
        intArrayOf(6, 3, 7, 7)
    )
    val characterX = 9
    val characterY = 7
    val itemX = 6
    val itemY = 1

    val result = sol.solution(rectangle, characterX, characterY, itemX, itemY)
    println(result)
}