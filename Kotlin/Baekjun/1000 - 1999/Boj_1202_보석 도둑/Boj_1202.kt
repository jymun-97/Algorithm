data class Jewelry(
    val weight: Int,
    val value: Int,
)

var n = 0
var k = 0
lateinit var jewelries: List<Jewelry>
lateinit var bags: List<Int>
val count = hashMapOf<Int, Int>()

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        k = this[1]
    }
    jewelries = List(n) {
        val (weight, value) = readLine().split(" ").map { it.toInt() }
        Jewelry(weight, value)
    }.sortedBy { -it.value }

    bags = hashSetOf<Int>().apply {
        addAll(List(k) { readLine().toInt() })
    }.sorted().also {
        k = it.size
        it.forEach { weight ->
            count[weight] = (count[weight] ?: 0) + 1
        }
    }
}

fun solve() {
    var sum = 0
    jewelries.forEach { jewelry ->
        if (bags.isEmpty()) return@forEach

        binarySearch(jewelry.weight)?.let {
            sum += jewelry.value

            count[it] = count[it]!! - 1
            if (count[it]!! == 0) count.remove(it)
        }
    }
    println(sum)
}

fun binarySearch(weight: Int): Int? {
    var l = 0
    var r = k - 1
    var target: Int? = null

    while (l <= r) {
        val mid = (l + r) / 2
        when {
            bags[mid] == weight && count[bags[mid]] != null -> return bags[mid]

            bags[mid] > weight -> {
                r = mid - 1
                count[bags[mid]]?.let {
                    target = bags[mid]
                }
            }

            else -> l = mid + 1
        }
    }
    return target
}

fun main() {
    input()
    solve()

    val test = mutableListOf<Int>()
    test.remove(0)
}