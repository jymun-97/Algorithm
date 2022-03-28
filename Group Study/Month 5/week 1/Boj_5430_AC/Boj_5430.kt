import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

val br = BufferedReader(InputStreamReader(System.`in`))
val sb = StringBuilder()

lateinit var p: String
lateinit var list: MutableList<Int>

fun input() {
    p = br.readLine()
    val size = br.readLine().toInt()

    list = mutableListOf()
    var st = StringTokenizer(br.readLine(), "[],")
    while (st.hasMoreTokens()) list.add(st.nextToken().toInt())
}

fun solve() {
    var flag = false
    p.forEach { c ->
        when (c) {
            'R' -> {
                flag = !flag
            }
            'D' -> {
                if (list.size == 0) {
                    sb.append("error").append('\n')
                    return
                }
                if (flag) list.removeLast() else list.removeFirst()
            }
        }
    }
    if (flag) list.reverse()
    sb.append(list.toString().replace(" ", "")).append('\n')
}

fun main() {
    var t = br.readLine().toInt()

    while (t-- > 0) {
        input()
        solve()
    }
    println(sb)
}