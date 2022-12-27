data class Problem(
    val alp: Int,
    val cop: Int,
    val plusArp: Int,
    val plusCop: Int,
    val time: Int,
) {
    fun isValid(alp: Int, cop: Int) = this.alp <= alp && this.cop <= cop && (plusArp > 0 || plusCop > 0)
}

class Solution {
    var maxAlp = 0
    var maxCop = 0
    lateinit var problems: List<Problem>
    lateinit var dp: Array<IntArray>

    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        this.problems = problems.map {
            val (alp, cop, plusAlp, plusCop, time) = it
            maxAlp = maxOf(maxAlp, alp)
            maxCop = maxOf(maxCop, cop)

            Problem(alp, cop, plusAlp, plusCop, time)
        }
        dp = Array(maxAlp + 1) { IntArray(maxCop + 1) { MAX } }
        dp[maxAlp][maxCop] = 0

        return dfs(alp, cop, -1, -1)
    }

    fun dfs(alp: Int, cop: Int, preAlp: Int, preCop: Int): Int {
        val alp = minOf(alp, maxAlp)
        val cop = minOf(cop, maxCop)

        return when {
            alp == preAlp && cop == preCop -> MAX

            alp == maxAlp && cop == maxCop -> 0

            dp[alp][cop] != MAX -> dp[alp][cop]

            else -> {
                problems.filter { it.isValid(alp, cop) }.forEach { problem ->
                    dp[alp][cop] = minOf(
                        dp[alp][cop],
                        dfs(alp + problem.plusArp, cop + problem.plusCop, alp, cop) + problem.time
                    )
                }
                dp[alp][cop] = minOf(
                    dp[alp][cop],
                    1 + dfs(alp, cop + 1, alp, cop),
                    1 + dfs(alp + 1, cop, alp, cop),
                )
                dp[alp][cop]
            }
        }
    }

    companion object {
        const val MAX = 1_000_000
    }
}