import java.util.*
import kotlin.math.max

data class User(
    val id: Int,
    var crime: Int,
) : Comparable<User> {

    override fun compareTo(other: User): Int {
        return if (crime != other.crime) other.crime - crime
        else id - other.id
    }
}

var n = 0
var mafia = 0
var ans = 0
val users = LinkedList<User>()
lateinit var arr: Array<IntArray>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    val crimes = readLine().split(" ").map { it.toInt() }
    arr = Array(n) { readLine().split(" ").map { it.toInt() }.toIntArray() }

    mafia = readLine().toInt()
    repeat(n) {
        users.add(
            User(it, crimes[it])
        )
    }
}

fun solve() {
    dfs(0)
    println(ans)
}

fun dfs(count: Int) {
    users.sort()
    ans = max(ans, count)

    if (users.size % 2 == 1) {
        if (users.peek().id == mafia) return
        val target = users.poll()
        dfs(count)
        users.add(target)
    }
    else {
        (0 until n).forEach { id ->
            if (id == mafia) return@forEach

            val target = users.find { it.id == id } ?: return@forEach
            users.apply {
                remove(target)
                forEach { it.crime += arr[target.id][it.id] }
                dfs(count + 1)
                forEach { it.crime -= arr[target.id][it.id] }
                add(target)
            }
        }
    }
}

fun main() {
    input()
    solve()
}