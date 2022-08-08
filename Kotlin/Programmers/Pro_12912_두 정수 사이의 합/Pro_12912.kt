class Solution {
    fun solution(a: Int, b: Int) =
        if (a <= b) (a .. b.toLong()).sum()
        else (b .. a.toLong()).sum()
}