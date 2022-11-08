import java.util.*
import kotlin.math.max

data class Range(
    val start: Int,
    val end: Int
)
var n = 0
val dp = IntArray(100001)
val ranges = LinkedList<Range>()

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    repeat(n) {
        val (start, end) = readLine().split(" ").map { it.toInt() }
        ranges.add(Range(start, end))
    }
}

fun solve() {
   for (i in ranges.first.end .. 100000) {
       if (ranges.isEmpty()) {
           println(dp[i - 1])
           return
       }
       if (ranges.first.end > i) dp[i] = if (i > 0) dp[i - 1] else 0
       else {
           val range = ranges.poll()
           if (i - range.start >= 0) {
               dp[i] = max(
                   dp[i - 1],
                   dp[i - range.start] + 1
               )
           }
           else dp[i] = if (i > 0) dp[i - 1] else 0
       }
       print("$i ")
       println((0..i).map { dp[it] })
   }
   println(dp.last())
}

fun main() {
    input()
    solve()
}