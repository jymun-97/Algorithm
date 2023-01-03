import kotlin.math.max

var n = 0
var k = 0
var ans = 0
var size = 0
lateinit var words: IntArray
lateinit var chars: CharArray

infix fun Int.on(i: Int) = this or (1 shl i)

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        k = this[1]
    }

    val charSet = hashSetOf<Char>()
    words = IntArray(n)
    repeat(n) { i ->
        readLine().forEach {
            words[i] = words[i] or (1 shl it - 'a')
            charSet.add(it)
        }
    }
    chars = charSet.toCharArray()
    size = chars.size
}

fun solve() {
    if (size <= k) {
        println(n)
        return
    }
    dfs(0, 0, 0)
    println(ans)
}

fun dfs(i: Int, count: Int, learned: Int) {
    if (i == size || count == k) {
        ans = max(
            ans,
            words.count { learned == learned or it }
        )
        return
    }
    dfs(i + 1, count, learned)
    dfs(i + 1, count + 1, learned on (chars[i] - 'a'))
}

fun main() {
    input()
    solve()
}