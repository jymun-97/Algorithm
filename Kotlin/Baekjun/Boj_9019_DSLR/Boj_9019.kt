import java.util.*

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

const val MOD = 10000
const val COMMANDS = "DSLR"

data class Number(
    val value: Int
) {
    val str = value.toString().padStart(4, '0')

    fun D() = Number(if (value * 2 >= MOD) value * 2 % MOD else value * 2)

    fun S() = Number(if (value == 0) MOD - 1 else value - 1)

    fun L() = Number("${str.substring(1)}${str[0]}".toInt())

    fun R() = Number("${str.last()}${str.substring(0, str.lastIndex)}".toInt())
}

lateinit var start: Number
lateinit var end: Number

fun input() = with(reader) {
    readLine().split(" ").map { it.toInt() }.let {
        start = Number(it[0])
        end = Number(it[1])
    }
}

fun solve() {
    val map = hashMapOf<Number, String>()
    val que = LinkedList<Number>().apply {
        add(start)
        map[start] = ""
    }
    while (que.isNotEmpty()) {
        val num = que.poll()
        if (num == end) break

        listOf(num.D(), num.S(), num.L(), num.R()).forEachIndexed { i, cand ->
            map[cand] = map[cand]?.let {
                return@forEachIndexed
            } ?: "${map[num]}${COMMANDS[i]}"
            que.add(cand)
        }
    }
    builder.append(map[end]).append('\n')
}

fun main() = with(reader) {
    repeat(readLine().toInt()) {
        input()
        solve()
    }
    println(builder)
}