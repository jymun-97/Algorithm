import kotlin.math.min

class Solution {
    fun solution(s: String): Int {
        val n = s.length

        var ans = n
        for (i in 1 .. n / 2) {
            val tokens = StringBuilder(s).windowed(i, i)
            ans = min(ans, run(tokens) + n % i)
        }
        return ans
    }

    fun run(tokens: List<String>) : Int {
        val sb = StringBuilder()
        var target = ""
        var count = 1
        tokens.forEach { token ->
            if (target == token) count++
            else {
                sb.append(target).append(if (count == 1) "" else count)
                target = token
                count = 1
            }
        }
        sb.append(target).append(if (count == 1) "" else count)

        return sb.length
    }
}

fun main() {
    val s = Solution()
    println(
        s.solution(
            "aabbaccc"
        )
    )
}