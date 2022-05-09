import java.util.*

var n = 0
var c = 0
lateinit var leftArr: IntArray
lateinit var rightArr: IntArray
val left = TreeMap<Int, Int>()
val right = TreeMap<Int, Int>()

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        c = nextToken().toInt()
    }
    val arr = readLine().split(' ').map { it.toInt() }

    leftArr = arr.subList(0, n / 2).toIntArray()
    rightArr = arr.subList(n / 2, n).toIntArray()
}

fun solve() {

}

fun dfs() {

}

fun main() {
    input()
    solve()
}