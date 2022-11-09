import java.util.*

lateinit var parentOf: IntArray

fun solve() = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    parentOf = IntArray(n + 1) { it }

    val ans = StringBuilder()
    repeat(m) {
        val (command, a, b) = readLine().split(" ").map { it.toInt() }
        if (command == 0) {
            union(a, b)
        } else {
            ans.append(
                if (find(a) == find(b)) "YES"
                else "NO"
            ).append('\n')
        }
    }
    println(ans)
}

fun union(a: Int, b: Int) {
    val pa = find(a)
    val pb = find(b)

    if (pa != pb) parentOf[pa] = pb
}

fun find(x: Int): Int = if (x == parentOf[x]) x else {
    parentOf[x] = find(parentOf[x])
    parentOf[x]
}

fun main() {
    solve()
}