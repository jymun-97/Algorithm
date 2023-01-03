
var n = 0
val first = hashMapOf<Int, Int>()
val second = hashMapOf<Int, Int>()
val third = hashMapOf<Int, Int>()
val fourth = hashMapOf<Int, Int>()
val a = hashMapOf<Int, Int>()
val b = hashMapOf<Int, Int>()

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    repeat(n) {
        readLine().split(" ").map { it.toInt() }.apply {
            first[this[0]] = (first[this[0]] ?: 0) + 1
            second[this[1]] = (second[this[1]] ?: 0) + 1
            third[this[2]] = (third[this[2]] ?: 0) + 1
            fourth[this[3]] = (fourth[this[3]] ?: 0) + 1
        }
    }
}

fun init() {
    first.forEach { (num, count) ->
        second.forEach { (num2, count2) ->
            a[num + num2] = (a[num + num2] ?: 0) + count * count2
        }
    }
    third.forEach { (num, count) ->
        fourth.forEach { (num2, count2) ->
            b[num + num2] = (b[num + num2] ?: 0) + count * count2
        }
    }
}

fun solve() {
    init()

    var ans = 0
    a.forEach { (num, count) ->
        if (b.containsKey(-num)) {
            ans += (count * b[-num]!!)
        }
    }
    println(ans)
}

fun main() {
    input()
    solve()
}