class Solution {
    fun solution(w: Int, h: Int): Long {
        var answer: Long = w * h.toLong()

        val gcd = GCD(w, h)
        val nw = w / gcd
        val nh = h / gcd

        answer -= (nw + nh - 1) * gcd

        return answer
    }

    fun GCD(a: Int, b: Int): Long = if (b == 0) a.toLong() else GCD(b, a % b)
}