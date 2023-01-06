data class Range(
    val start: Int,
    val end: Int,
) {
    fun commonWith(other: Range): Range? = when {
        start >= other.end || end <= other.start -> null

        else -> Range(
            maxOf(start, other.start),
            minOf(end, other.end)
        )
    }
}

class Solution {
    fun solution(targets: Array<IntArray>): Int {
        var ans = 1

        targets.map { Range(it[0], it[1]) }.sortedBy { it.start }.reduce { common, target ->
            target.commonWith(common) ?: run {
                ans ++
                target
            }
        }

        return ans
    }
}

fun main() {
    val targets = arrayOf(
        intArrayOf(4,5),
        intArrayOf(4,8),
        intArrayOf(10,14),
        intArrayOf(11,13),
        intArrayOf(5,12),
        intArrayOf(3,7),
        intArrayOf(1,4)
    )

    println(
        Solution().solution(targets)
    )
}