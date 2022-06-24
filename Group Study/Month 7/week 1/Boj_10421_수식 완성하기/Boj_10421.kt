var n = 0
var k = 0
var ans = 0

var first = 0
var second = 0

lateinit var nDigits: List<Int>
lateinit var nums: List<String>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    nDigits = readLine().split(' ').map { it.toInt() }

    k = readLine().toInt()
    nums = readLine().split(' ')
}

fun solve() {
    makeFirst(0, "")
    println(ans)
}

fun makeFirst(x: Int, num: String) {
    if (x == nDigits[0]) {
        first = num.toInt()
        makeSecond(0, "")

        return
    }
    nums.forEach { makeFirst(x + 1, num + it) }
}

fun makeSecond(x: Int, num: String) {
    if (x == nDigits[1]) {
        second = num.toInt()
        check()

        return
    }

    nums.forEach loop@ { c ->
        val subResult = (first * c.toInt()).toString()

        if (subResult.length != nDigits[x + 2]) return@loop
        subResult.forEach { if (!nums.contains(it.toString())) return@loop }

        makeSecond(x + 1, num + c)
    }
}

fun check()  {
    val result = (first * second).toString()
    if (result.length != nDigits.last()) return

    result.forEach { if (!nums.contains(it.toString())) return }

    ans++
}

fun main() {
    input()
    solve()
}