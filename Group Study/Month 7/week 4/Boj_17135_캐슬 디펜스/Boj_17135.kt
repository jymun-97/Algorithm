import java.util.*
import kotlin.collections.HashSet
import kotlin.math.abs
import kotlin.math.max

data class Target(
    var row : Int,
    var col : Int
)
var n = 0
var m = 0
var limit = 0
var ans = 0
val set = hashSetOf<Target>()
val selected = hashSetOf<Int>()

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        m = nextToken().toInt()
        limit = nextToken().toInt()
    }
    for (i in 0 until n) {
        readLine().split(" ").forEachIndexed { j, s ->
            if (s == "1") set.add(Target(i, j))
        }
    }
}

fun solve() {
    dfs(0, 0)
    print(ans)
}

fun dfs(k: Int, start: Int) {
    if (k == 3) {
        run()
    }

    for (i in start until m) {
        selected.add(i)
        dfs(k + 1, i + 1)
        selected.remove(i)
    }
}

fun run() {
    var targets = HashSet(set)
    var killed = 0

    while (targets.isNotEmpty()) {
        val attacked = hashSetOf<Target>()
        selected.forEach {  pos ->
            attack(targets, pos)?.let {
                attacked.add(it)
            }
        }
        killed += attacked.size
        targets.removeAll(attacked)
        targets = moveDown(targets)
    }

    ans = max(ans, killed)
}

fun attack(targets: HashSet<Target>, pos: Int) : Target? {
    val cand = targets.filter { n - it.row + (abs(pos - it.col)) <= limit }
        .sortedWith(compareBy( { n - it.row + (abs(pos - it.col)) }, { it.col } ))

    return if (cand.isEmpty()) null else cand[0]
}

fun moveDown(targets: HashSet<Target>) = targets
    .filter { it.row + 1 < n }
    .map { Target(it.row + 1, it.col) }
    .toHashSet()

fun main() {
    input()
    solve()
}