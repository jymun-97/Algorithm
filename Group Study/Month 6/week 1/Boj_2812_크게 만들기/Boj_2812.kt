import java.io.*
import java.util.*

var n = 0
var k = 0
var list = LinkedList<Int>()
lateinit var nums: IntArray

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        k = nextToken().toInt()
    }
    nums = IntArray(n)
    readLine().forEachIndexed { index, c -> nums[index] = c - '0' }
    list.add(nums[0])
}

fun solve() {
    var index = 1
    var count = 0

    while (count < k) {
        if (index == n) {
            while (count < k) {
                list.pollLast()
                count++
            }
            break
        }
        while (count < k && list.isNotEmpty() && list.last < nums[index]) {
            list.pollLast()
            count++
        }
        list.add(nums[index++])
    }
    val sb = StringBuilder()
    list.forEach { sb.append(it) }

    while (index < n) sb.append(nums[index++])

    print(sb)
}

fun main() {
    input()
    solve()
}