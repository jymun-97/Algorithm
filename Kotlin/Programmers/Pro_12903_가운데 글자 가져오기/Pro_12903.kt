class Solution {
    fun solution(s: String) : String {
        val size = s.length
        return s.substring(
            startIndex = if (size % 2 == 0) size / 2 - 1 else size / 2,
            endIndex = if (size % 2 == 0) size / 2 + 1 else size / 2 + 1
        )
    }
}