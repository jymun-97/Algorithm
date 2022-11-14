import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs
import kotlin.math.min

var dest = 0
val inValidButtons = HashSet<Int>()

fun input() = with(System.`in`.bufferedReader()) {
    dest = readLine().toInt()
    val size = readLine().toInt()
    if (size > 0) {
        inValidButtons.addAll(readLine().split(" ").map { it.toInt() })
    }
}

fun solve() {
    var ans = abs(dest - 100)
    if (inValidButtons.size < 10) {
        for (target in dest downTo 0) {
            val cand = target.toString().length + dest - target
            if (cand >= ans) break
            if (check(target)) {
                ans = cand
                break
            }
        }
        for (target in dest..Int.MAX_VALUE) {
            val cand = target.toString().length + target - dest
            if (cand >= ans) break
            if (check(target)) {
                ans = cand
                break
            }
        }
    }
    println(ans)
}

fun check(target: Int) =
    target.toString().toCharArray().none { inValidButtons.contains(it.digitToInt()) }

fun main() {
    input()
    solve()
}