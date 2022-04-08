import java.util.*
import kotlin.collections.ArrayList

var n = 0
var c = 0

lateinit var listA : List<Int>
lateinit var listB : List<Int>
lateinit var combi : ArrayList<Int>
lateinit var combiA : ArrayList<Int>
lateinit var combiB : ArrayList<Int>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        c = nextToken().toInt()
    }
    val nums = readLine().split(' ').map { it.toInt() }
    listA = nums.subList(0, n / 2)
    listB = nums.subList(n / 2, n)

    combiA = arrayListOf()
    combiB = arrayListOf()
}

fun solve() {
    initCombiA()
    initCombiB()

    var ans = 0
    run {
        combiB.forEach { weight ->
            val limit = c - weight

            if (limit < 0) return@run

            val count = upperBound(limit)
            ans += count
        }
    }
    println(ans)
}

fun upperBound(target: Int) : Int {
    var left = 0
    var right = combiA.size

    while (left < right) {
        val mid = (left + right) / 2

        if (combiA[mid] > target) {
            right = mid
        }
        else left = mid + 1
    }
    return left
}

fun dfs(k: Int, list: List<Int>, selected: ArrayList<Int>) {
    if (k == list.size) {
        combi.add(selected.sum())
        return
    }

    selected.add(list[k])
    dfs(k + 1, list, selected)

    selected.remove(list[k])
    dfs(k + 1, list, selected)
}

fun initCombiA() {
    combi = arrayListOf()
    dfs(0, listA, arrayListOf())

    combiA.apply {
        addAll(combi)
        sort()
    }
}

fun initCombiB() {
    combi = arrayListOf()
    dfs(0, listB, arrayListOf())
    combiB.apply {
        addAll(combi)
        sort()
    }
}

fun main() {
    input()
    solve()
}