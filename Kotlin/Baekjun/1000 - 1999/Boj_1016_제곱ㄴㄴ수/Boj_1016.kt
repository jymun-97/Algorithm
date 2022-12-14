import kotlin.math.sqrt

var min = 0L
var max = 0L
lateinit var flag: BooleanArray

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toLong() }.apply {
        min = this[0]
        max = this[1]
    }
    flag = BooleanArray((max - min + 1).toInt())
}

fun solve() {
    for (i in 2 .. sqrt(max.toDouble()).toLong()) {
        val pow = i * i
        var target = if (min % pow == 0L) min else pow * (min / pow + 1)

        while (target <= max) {
            flag[(target - min).toInt()] = true
            target += pow
        }
    }
    println(flag.count { !it })
}

fun main() {
    input()
    solve()
}