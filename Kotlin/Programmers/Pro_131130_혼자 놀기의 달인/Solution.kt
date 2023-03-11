class Solution {
    var n = 0
    lateinit var cardBox: IntArray
    lateinit var visit: BooleanArray

    fun solution(cards: IntArray): Int {
        n = cards.size
        cardBox = cards.map { it - 1 }.toIntArray()

        var ans = 0
        for (selected in 0 until n) {
            visit = BooleanArray(n)

            val score = getCount(selected) * (0 until n).maxOf { getCount(it) }
            ans = maxOf(ans, score)
        }

        return ans
    }

    fun getCount(i: Int): Int {
        var index = i
        var count = 0

        while (!visit[index]) {
            visit[index] = true
            index = cardBox[index]

            count++
        }

        return count
    }
}