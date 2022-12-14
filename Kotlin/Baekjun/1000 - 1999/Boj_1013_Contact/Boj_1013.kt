import java.util.*

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

var input = ""
val regex = "((100+1+)|(01))+".toRegex()

fun input() = with(reader) {
    input = readLine()
}

fun solve() {
    builder.append(
        if (input.matches(regex)) "YES"
        else "NO"
    ).append('\n')
}

fun main() = with(reader) {
    val t = readLine().toInt()
    repeat(t) {
        input()
        solve()
    }
    println(builder)
}