import java.util.*

var n = 0
var m = 0
lateinit var apple: Array<IntArray>
lateinit var banana: Array<IntArray>
lateinit var dp: Array<IntArray>

val dir = arrayOf(
    arrayOf(-1, 0),
    arrayOf(0, 1),
    arrayOf(-1, 1)
)

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
    }

    apple = Array(n) { IntArray(m) }
    banana = Array(n) { IntArray(m) }
    dp = Array(n) { IntArray(m) }

    for (i in 0 until n) {
        val tokens = readLine().split(" ")

        for (j in 0 until m) {
            val token = tokens[j]
            when (token[0]) {
                'A' -> apple[i][j] = token[1].digitToInt()

                'B' -> banana[i][j] = token[1].digitToInt()
            }
        }
    }
}

fun solve() {
    val que = LinkedList<Int>()
    que.add(0)
    que.add(0)

    while (que.isNotEmpty()) {
        val row = que.poll()
        val col = que.poll()

        if (row == n - 1 || col == m - 1) continue

        dir.forEach {
            val nr = it[0]
            val nc = it[1]


        }
    }
}

fun main() {
    input()
    solve()
}