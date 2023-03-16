class Solution {
    lateinit var score: List<Int>
    lateinit var dp: Array<IntArray>

    fun solution(target: Int): IntArray {
        initDP()

        for (score in 1..target) {
            if (dp[score][0] != Int.MAX_VALUE) continue

            addSingle(score)
            addDouble(score)
            addTriple(score)
        }

        return dp[target]
    }

    fun initDP() {
        dp = Array(SIZE + 1) { IntArray(2) { Int.MAX_VALUE } }
        dp[BULL] = intArrayOf(1, 1)

        initSingle()
        initDouble()
        initTriple()
    }

    fun initSingle() = (1..MAX).forEach { single ->
        dp[single] = intArrayOf(1, 1)
    }

    fun initDouble() = (1..MAX).map { it * 2 }.forEach { double ->
        if (dp[double][0] != 1) {
            dp[double] = intArrayOf(1, 0)
        }
    }

    fun initTriple() = (1..MAX).map { it * 3 }.forEach { triple ->
        if (dp[triple][0] != 1) {
            dp[triple] = intArrayOf(1, 0)
        }
    }

    fun addScore(target: Int, score: Int, flag: Int) = when {
        target <= score -> {}

        dp[target][0] < dp[target - score][0] + 1 -> {}

        dp[target][0] == dp[target - score][0] + 1 -> dp[target][1] = maxOf(dp[target][1], dp[target - score][1] + flag)

        else -> {
            dp[target][0] = dp[target - score][0] + 1
            dp[target][1] = dp[target - score][1] + flag
        }
    }

    fun addSingle(target: Int) {
        addScore(target, BULL, 1)
        (1..MAX).forEach { single -> addScore(target, single, 1) }
    }

    fun addDouble(target: Int) = (1..MAX).map { it * 2 }.forEach { double ->
        addScore(target, double, 0)
    }

    fun addTriple(target: Int) = (1..MAX).map { it * 3 }.forEach { triple ->
        addScore(target, triple, 0)
    }

    companion object {
        const val SIZE = 100000
        const val MAX = 20
        const val BULL = 50
    }
}