class Solution {
    var n = 0
    var left = 0L
    var right = 0L
    var ans = 0
    lateinit var pow: LongArray

    fun solution(n: Int, l: Long, r: Long): Int {
        this.n = n
        this.left = l - 1
        this.right = r - 1
        initPow()

        dfs(0, 0, '1')

        return ans
    }

    fun initPow() {
        pow = LongArray(n + 1)
        pow[0] = 1L

        for (i in 1..n) pow[i] = pow[i - 1] * 5
    }

    fun dfs(depth: Int, idx: Long, bit: Char): Unit = when {
        bit == '0' -> {}

        depth == n -> ans += if (idx in left..right) 1 else 0

        !isValid(depth, idx) -> {}

        else -> "11011".forEachIndexed { offset, bit ->
            dfs(depth + 1, idx * 5 + offset, bit)
        }
    }

    fun isValid(depth: Int, idx: Long): Boolean {
        val from = idx * pow[n - depth]
        val to = from + pow[n - depth] - 1

        return maxOf(from, left) <= minOf(to, right)
    }
}