class Solution {
    fun solution(s: String): String = s.split(" ").map {
        it.toCharArray().mapIndexed { index, c ->
            if (index % 2 == 0) c.uppercaseChar()
            else c.lowercaseChar()
        }.joinToString("")
    }.fold("") { acc, s -> "$acc$s " }.dropLast(1)
}