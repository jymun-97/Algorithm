
var n = 0
var m = 0
var ans = 0
lateinit var know: Array<Boolean?>
lateinit var participants: List<HashSet<Int>>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.let {
        n = it[0]
        m = it[1]
    }
    know = Array(n + 1) { null }

    readLine().split(" ").map { it.toInt() }.drop(1).forEach {
        know[it] = true
    }
    participants = List(m) { HashSet(readLine().split(" ").map { it.toInt() }.drop(1)) }
}

fun solve() {
    dfs(0, 0)

    println(ans)
}

fun dfs(k: Int, count: Int): Unit = when {
    m - k + count <= ans -> {}

    k == m -> ans = count

    participants[k].all { know[it] == null } -> {
        val temp = know.clone()

        participants[k].forEach { know[it] = false }
        dfs(k + 1, count + 1)
        participants[k].forEach { know[it] = true }
        dfs(k + 1, count)

        know = temp
    }

    participants[k].any { know[it] == true } && participants[k].none { know[it] == false } -> {
        val temp = know.clone()

        participants[k].forEach { know[it] = true }
        dfs(k + 1, count)

        know = temp
    }

    participants[k].any { know[it] == false } && participants[k].none { know[it] == true } -> {
        val temp = know.clone()

        participants[k].forEach { know[it] = false }
        dfs(k + 1, count + 1)

        know = temp
    }

    else -> {}
}

fun main() {
    input()
    solve()
}