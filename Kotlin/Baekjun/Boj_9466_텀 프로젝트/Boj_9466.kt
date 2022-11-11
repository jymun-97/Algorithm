import java.util.*
import kotlin.collections.HashSet

val sb = StringBuilder()
var br = System.`in`.bufferedReader()

data class Person(
    val idx: Int,
    val to: Int,
    var indeg: Int = 0
)
var n = 0
lateinit var people: List<Person>

fun input() = with(br) {
    n = readLine().toInt()
    people = readLine().split(" ").mapIndexed { index, s ->
        Person(index + 1, s.toInt())
    }
    people.forEach {
        people[it.to - 1].indeg++
    }
}

fun solve() {
    var ans = 0
    val que = LinkedList<Person>()
    que.addAll(people.filter { it.indeg == 0 })

    while (que.isNotEmpty()) {
        val person = que.poll()
        ans++

        val target = people[person.to - 1]
        if (--target.indeg == 0) {
            que.add(target)
        }
    }

    sb.append(ans).append('\n')
}

fun main() = with(br) {
    val nCase = readLine().toInt()
    repeat(nCase) {
        input()
        solve()
    }
    print(sb)
}