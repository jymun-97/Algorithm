import java.io.*
import java.util.*
import kotlin.math.pow

var k = 0
var index = 0
var size = 0

lateinit var num: StringBuilder

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        num = StringBuilder((nextToken().toLong() + 1).toString())
        k = nextToken().toInt()
    }
    size = num.length
    index = size - 1
}

fun solve() {
    if (size < k) {
        num.clear()
        for (i in 0 until k) num.append(5)
    }
    while (true) {
        if (getCount() >= k) break

        convert()
    }
    print(num)
}

fun convert() {
    when (num[index]) {
        '5' -> index--

        in ('0'..'4') -> num[index--] = '5'

        else -> {
            val temp = num.toString().toLong() + (10.0).pow(size - index).toLong()
            num = StringBuilder(temp.toString())
            if (num.length > size) {
                size = num.length
                index++
            }
            num[index] = '0'
        }
    }
}

fun getCount() = num.count { it == '5' }

fun main() {
    input()
    solve()
}