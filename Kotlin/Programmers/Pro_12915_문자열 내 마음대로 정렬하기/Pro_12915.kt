class Solution {
    fun solution(strings: Array<String>, n: Int) =
        strings.sortedWith(compareBy({ it[n] }, { it }))
}