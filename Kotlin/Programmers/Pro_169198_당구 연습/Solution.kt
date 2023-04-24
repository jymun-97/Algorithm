import kotlin.math.absoluteValue

fun Int.square() = this * this

data class Point(
    val x: Int,
    val y: Int
) {
    fun distanceTo(
        X: Int,
        Y: Int,
        target: Point
    ): Int = when {
        x == target.x && y < 0 && y.absoluteValue < target.y -> Int.MAX_VALUE

        x == target.x && y > Y && y - Y < Y - target.y -> Int.MAX_VALUE

        y == target.y && x < 0 && x.absoluteValue < target.x -> Int.MAX_VALUE

        y == target.y && x > X && x - X < X - target.x -> Int.MAX_VALUE

        else -> (target.x - x).square() + (target.y - y).square()
    }

    fun moved(X: Int, Y: Int) = listOf(
        Point(-x, y),
        Point(2 * X - x, y),
        Point(x, -y),
        Point(x, 2 * Y - y)
    )
}

class Solution {
    fun solution(m: Int, n: Int, startX: Int, startY: Int, balls: Array<IntArray>): IntArray {
        val point = Point(startX, startY)
        return balls.map { Point(it[0], it[1]).moved(m, n).minOf { it.distanceTo(m, n, point) } }.toIntArray()
    }
}