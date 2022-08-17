class Solution {
    fun solution(n: Int) = CharArray(n) { i ->
        if (i % 2 == 0) '수'
        else '박'
    }.concatToString()
}