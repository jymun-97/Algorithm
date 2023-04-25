data class City(
    var gold: Int,
    var silver: Int,
    val limit: Int,
    val time: Int
)
class Solution {
    var n = 0
    var gold = 0
    var silver = 0
    lateinit var cities: List<City>

    fun solution(a: Int, b: Int, g: IntArray, s: IntArray, w: IntArray, t: IntArray): Long {
        n = g.size
        gold = a
        silver = b
        cities = List(n) { City(g[it], s[it], w[it], t[it]) }

        var left = 0L
        var right = 400_000_000_000_000L

        while (left < right - 1) {
            val mid = (left + right) / 2

            if (isPossible(mid)) right = mid
            else left = mid
        }

        return right
    }

    fun isPossible(time: Long): Boolean {
        var total = 0L
        var goldSum = 0L
        var silverSum = 0L

        cities.forEach { city ->
            val count = if (time / city.time % 2 == 0L) time / city.time / 2 else time / city.time / 2 + 1
            val temp = minOf(count * city.limit, city.gold + city.silver.toLong())

            total += temp
            goldSum += minOf(temp, city.gold.toLong())
            silverSum += minOf(temp, city.silver.toLong())
        }

        return gold + silver <= total && gold <= goldSum && silver <= silverSum
    }
}