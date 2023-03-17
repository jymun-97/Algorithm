class Solution {
    val want = hashMapOf<String, Int>()
    val paid = hashMapOf<String, Int>()

    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        init(want, number, discount)

        var ans = if (check()) 1 else 0
        for (i in 10 until discount.size) {
            paid[discount[i - 10]] = paid[discount[i - 10]]!! - 1
            paid[discount[i]] = (paid[discount[i]] ?: 0) + 1

            if (check()) ans++
        }

        return ans
    }

    fun init(
        name: Array<String>,
        number: IntArray,
        discount: Array<String>)
    {
        name.forEachIndexed { i, target ->
            want[target] = number[i]
        }
        repeat(10) {
            paid[discount[it]] = (paid[discount[it]] ?: 0) + 1
        }
    }

    fun check(): Boolean = want.all { it.value <= (paid[it.key] ?: 0) }
}