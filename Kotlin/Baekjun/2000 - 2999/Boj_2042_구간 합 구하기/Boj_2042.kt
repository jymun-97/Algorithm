import java.util.*

var n = 0
var m = 0
var k = 0
lateinit var nums: LongArray
lateinit var commands: Array<String>
lateinit var tree: LongArray

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        k = nextToken().toInt()
    }
    nums = LongArray(n + 1) { if (it == 0) 0 else readLine().toLong() }
    commands = Array(m + k) { readLine() }
    tree = LongArray(4 * n + 1)
}

fun solve() {
    init(1, n, 1)
    val sb = StringBuilder()

    commands.forEach { command ->
        val (a, b, c) = command.split(" ").map { it.toLong() }
        if (a == 1L) {
            update(1, n, 1, b.toInt(), c - nums[b.toInt()])
            nums[b.toInt()] = c
        } else {
            sb.append(
                sum(1, n, 1, b.toInt(), c.toInt())
            ).append('\n')
        }
    }
    print(sb)
}

fun init(start: Int, end: Int, node: Int): Long {
    tree[node] = when (start) {
        end -> nums[start]
        else -> {
            val mid = (start + end) / 2
            init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1)
        }
    }
    return tree[node]
}

fun sum(start: Int, end: Int, node: Int, left: Int, right: Int): Long = when {
    start > right || end < left -> 0

    start >= left && end <= right -> tree[node]

    else -> {
        val mid = (start + end) / 2
        sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right)
    }
}

fun update(start: Int, end: Int, node: Int, index: Int, diff: Long)  {
    if (index !in start..end) return

    tree[node] += diff
    if (start == end) return

    val mid = (start + end) / 2
    update(start, mid, node * 2, index, diff)
    update(mid + 1, end, node * 2 + 1, index, diff)
}

fun main() {
    input()
    solve()
}