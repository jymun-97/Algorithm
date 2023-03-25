class Solution {
    var n = 0
    lateinit var nums: IntArray
    lateinit var indexOf: Array<ArrayList<Int>>

    fun solution(a: IntArray): Int {
        init(a)
        return if (nums.size <= 1) 0 else (0 until n).maxOf { getSizeOfStarSequence(it) }
    }

    fun init(a: IntArray) {
        n = a.size
        nums = a
        indexOf = Array(a.size) { arrayListOf() }
        a.forEachIndexed { i, num ->
            indexOf[num].add(i)
        }
    }

    fun getSizeOfStarSequence(common: Int): Int {
        if (indexOf[common].size == 0) return 0

        val flags = Array(indexOf[common].size) { BooleanArray(2) }

        flags[0][0] = indexOf[common][0] != 0
        flags[0][1] = true

        for (i in 1 until indexOf[common].size) {
            val pre = indexOf[common][i - 1]
            val now = indexOf[common][i]

            when {
                now - pre > 2 -> {
                    flags[i][0] = true
                    flags[i][1] = true
                }
                now - pre == 1 -> {
                    flags[i - 1][1] = false
                    flags[i][1] = true
                }
                now - pre == 2 && !flags[i - 1][0] -> flags[i][1] = true

                else -> {
                    flags[i][0] = true
                    flags[i][1] = true
                }
            }
        }

        flags.last()[1] = indexOf[common].last() != n - 1
        return flags.count { it[0] or it[1] } * 2
    }
}

fun main() {
    println(
        Solution().solution(
            intArrayOf(0,1,0,1,2,1)
        )
    )
}