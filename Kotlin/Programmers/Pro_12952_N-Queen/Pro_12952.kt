import java.util.LinkedList
import kotlin.math.abs

data class Queen(
    val row: Int,
    val col: Int
)
class Solution {
    var n = 0
    var answer = 0

    fun solution(n: Int): Int {
        this.n = n
        dfs(0, LinkedList())

        return answer
    }

    fun dfs(k: Int, queens: LinkedList<Queen>) {
        if (k == n) {
            answer++
            return
        }
        repeat(n) { col ->
            if (queens.all { it.col != col && k - it.row != abs(col - it.col) }) {
                queens.add(Queen(k, col))
                dfs(k + 1, queens)
                queens.pollLast()
            }
        }
    }
}