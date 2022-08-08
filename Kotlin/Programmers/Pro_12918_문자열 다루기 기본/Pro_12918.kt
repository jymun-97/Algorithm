class Solution {
    fun solution(s: String) = s.all { it.isDigit() } && (s.length == 4 || s.length == 6)
}