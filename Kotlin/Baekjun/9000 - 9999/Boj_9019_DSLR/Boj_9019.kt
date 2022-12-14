import java.util.*

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

const val MOD = 10000
const val COMMAND = "DSLR"

var num = 0
var target = 0
lateinit var parentOf: IntArray
lateinit var route: CharArray

fun Int.D() = if (this * 2 >= MOD) this * 2 % MOD else this * 2
fun Int.S() = if (this - 1 < 0) MOD - 1 else this - 1
fun Int.L() = this % 1000 * 10 + this / 1000
fun Int.R() = this % 10 * 1000 + this / 10

fun input() = with(reader) {
    readLine().split(" ").map { it.toInt() }.apply {
        num = this[0]
        target = this[1]
    }
    parentOf = IntArray(MOD) { -1 }
    route = CharArray(MOD)
}

fun solve() {
    val que = LinkedList<Int>().apply {
        add(num)
        parentOf[num] = num
    }
    while (que.isNotEmpty()) {
        with(que.poll()) {
            if (this == target) {
                builder.append(getRouteString()).append('\n')
                return
            }
            listOf(D(), S(), L(), R()).forEachIndexed { i, it ->
                if (parentOf[it] != -1) return@forEachIndexed

                que.add(it)
                parentOf[it] = this
                route[it] = COMMAND[i]
            }
        }
    }
}

fun getRouteString(): String {
    val que = LinkedList<Char>()
    var from = target

    while (from != num) {
        que.addFirst(route[from])
        from = parentOf[from]
    }
    return que.joinToString("")
}

fun main() = with(reader) {
    repeat(readLine().toInt()) {
        input()
        solve()
    }
    println(builder)
}