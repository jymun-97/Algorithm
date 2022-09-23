class Solution {
    fun solution(s: String): String =
        s.lowercase()
            .split(" ")
            .joinToString(" ") {
                it.replaceFirstChar(Char::titlecase)
            }
}