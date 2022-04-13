data class Info(
    val id: Int,
    val rate: Double
)

class Solution {
    var nStage = 0
    var nUser = 0
    lateinit var unCleared: IntArray
    lateinit var tried: IntArray

    fun solution(N: Int, stages: IntArray): IntArray {
        nStage = N
        nUser = stages.size

        unCleared = IntArray(nStage + 2)
        tried = IntArray(nStage + 1)

        stages.forEach { unCleared[it]++ }

        tried[1] = nUser
        for (i in 2..nStage) {
            tried[i] = tried[i - 1] - unCleared[i - 1]
        }

        var list = mutableListOf<Info>()
        for (i in 1..nStage) {
            list.add(
                Info(
                    id = i,
                    rate = if (tried[i] == 0) 0.0 else unCleared[i] / tried[i].toDouble()
                )
            )
        }

        val ans = IntArray(nStage)
        list.sortWith(compareByDescending { it.rate })
            .forEachIndexed { index, info ->
                ans[index] = info.id
            }
        list.forEachIndexed { index, info ->
            ans[index] = info.id
        }
        return ans
    }
}

fun main() {
    val s = Solution()
    var ans = s.solution(
        5,
<<<<<<< HEAD
        intArrayOf(2, 1, 2, 6, 2, 4, 3, 3),
=======
        intArrayOf(2,1,2,6,2,4,3,3),
>>>>>>> a27bc4d03a30b6f80a161ec2d24f06b91b2ac3c4
    )
    println(ans.contentToString())
}