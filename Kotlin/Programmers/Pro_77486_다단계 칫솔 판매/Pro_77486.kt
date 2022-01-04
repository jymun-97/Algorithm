import java.util.HashMap

internal class Solution {
    var toIndex = HashMap<String, Int>()
    lateinit var parentOf: IntArray
    lateinit var result: IntArray

    fun solution(
        enroll: Array<String>,
        referral: Array<String>,
        seller: Array<String>,
        amount: IntArray
    ): IntArray {
        val n = enroll.size
        parentOf = IntArray(n)
        result = IntArray(n)
        toIndex["-"] = -1

        for (i in 0 until n) {
            toIndex[enroll[i]] = i
            parentOf[i] = toIndex[referral[i]]!!
        }
        for (i in amount.indices) {
            val sellerIndex = toIndex[seller[i]]!!
            dfs(sellerIndex, amount[i] * 100)
        }
        return result
    }

    fun dfs(from: Int, money: Int) {
        if (from == -1) return
        if (money < 10) {
            result[from] += money
            return
        }
        result[from] += money - money / 10
        dfs(parentOf[from], money / 10)
    }
}