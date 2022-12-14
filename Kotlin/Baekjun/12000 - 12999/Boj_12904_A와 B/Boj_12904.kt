var str = ""
var target = ""

fun input() = with(System.`in`.bufferedReader()) {
    str = readLine()
    target = readLine()
}

fun solve() {
    while (str.length < target.length) {
        val last = target.last()
        target = target.dropLast(1)

        if (last == 'B') target = target.reversed()
    }

    println(
        if (str == target) 1
        else 0
    )
}

fun main() {
    input()
    solve()
}