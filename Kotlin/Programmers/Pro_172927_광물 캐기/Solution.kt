enum class Mineral(val strength: Int) {
    DIAMOND(25), IRON(5), STONE(1)
}

enum class Pick(val strength: Int) {
    DIAMOND(25), IRON(5), STONE(1)
}

class Solution {
    lateinit var countOfPicks: IntArray
    lateinit var minerals: List<Mineral>
    var n = 0
    var remain = 0
    var ans = Int.MAX_VALUE

    fun solution(picks: IntArray, minerals: Array<String>): Int {
        this.countOfPicks = picks
        this.minerals = minerals.map {
            when (it) {
                "diamond" -> Mineral.DIAMOND
                "iron" -> Mineral.IRON
                else -> Mineral.STONE
            }
        }
        remain = countOfPicks.sum()
        n = minerals.size

        dfs(0, Pick.DIAMOND, remain, 0, 0)
        return ans
    }

    fun dfs(i: Int, pick: Pick, remain: Int, count: Int, sum: Int): Unit = when {
        sum >= ans -> {}

        (count == 0 && remain == 0) || i == n -> ans = sum

        count > 0 -> dfs(i + 1, pick, remain, count - 1, sum + getTiredness(pick, minerals[i]))

        else -> Pick.values().forEach { newPick ->
            if (countOfPicks[newPick.ordinal] > 0) {
                countOfPicks[newPick.ordinal]--
                dfs(i, newPick, remain - 1, 5, sum)
                countOfPicks[newPick.ordinal]++
            }
        }
    }

    fun getTiredness(pick: Pick, mineral: Mineral): Int = maxOf(mineral.strength / pick.strength, 1)
}