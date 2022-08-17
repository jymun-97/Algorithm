class Solution {
    fun solution(s: String, n: Int) = s.toCharArray()
        .map {
            when (it) {
                in 'a' .. 'z' -> if (it + n > 'z') it + n - 26 else it + n
                in 'A' .. 'Z' -> if (it + n > 'Z') it + n - 26 else it + n
                else -> ' '
            }
        }
        .joinToString("")
}