class Solution {
    fun solution(s: String) = s.toCharArray()
        .sortedByDescending { it.code }
        .fold("") { acc, c -> "$acc$c" }
}