var n = 0
lateinit var arr: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    arr = readLine()
        .split(' ')
        .map { it.toInt() }
        .sorted()
        .toIntArray()
}

fun solve() {
    var sum = 1
    run {
        arr.forEach {
            if (sum < it) return@run
            sum += it
        }
    }
    println(sum)
}

fun main() {
    input()
    solve()
}