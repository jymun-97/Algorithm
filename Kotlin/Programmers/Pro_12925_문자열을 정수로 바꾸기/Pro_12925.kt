class Solution {
    fun solution(s: String) : Int = when {
        s[0] == '+' -> s.substring(1).toInt()

        s[0] == '-' -> s.substring(1).toInt() * -1

        else -> s.toInt()
    }
}