import java.util.LinkedList

data class Tower(
    val id: Int,
    val height: Int,
) {
    var target = 0
}

val stack = LinkedList<Tower>()
var n = 0
lateinit var towers: List<Tower>

fun input() = with(System.`in`.bufferedReader()) {
    n = readLine().toInt()
    towers = readLine()
        .split(" ")
        .mapIndexed { id, height ->
            Tower(id + 1, height.toInt())
        }
}

fun solve() {
    var idx = 0
    while (idx < towers.size) {
        val tower = towers[idx]
        when {
            stack.isEmpty() -> {
                tower.target = 0
                stack.add(tower)
                idx++
            }
            stack.peekLast().height < tower.height -> stack.pollLast()

            else -> {
                tower.target = stack.peekLast().id
                stack.add(tower)
                idx++
            }
        }
    }
    print(
        buildString {
            towers.map { it.target }.forEach { ans ->
                append(ans).append(' ')
            }
        }
    )
}

fun main() {
    input()
    solve()
}