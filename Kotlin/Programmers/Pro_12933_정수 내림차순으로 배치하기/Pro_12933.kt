class Solution {
    fun solution(n: Long): Long = n.toString()
        .toCharArray()
        .sortedDescending()
        .joinToString("")
        .toLong()
}