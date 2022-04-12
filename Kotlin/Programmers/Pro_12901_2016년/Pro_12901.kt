import java.time.LocalDate

class Solution {
    fun solution(a: Int, b: Int): String =
        LocalDate.of(2016, a, b).dayOfWeek.toString().substring(0, 3)
}