import kotlin.math.sqrt

class Solution {
    fun solution(left: Int, right: Int) =
        (left .. right).fold(0) { ans, num ->
            if (sqrt(num + 0.0) % 1 == 0.0) ans - num else ans + num
        }
}