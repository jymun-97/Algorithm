import java.util.TreeMap

val reader = System.`in`.bufferedReader()
val builder = StringBuilder()

lateinit var commands: List<Pair<Char, Int>>
lateinit var map: TreeMap<Int, Int>

fun input() = with(reader) {
    commands = List(readLine().toInt()) {
        val (command, value) = readLine().split(" ")
        command[0] to value.toInt()
    }
    map = TreeMap()
}

fun solve() {
    commands.forEach {
        val (command, value) = it
        when (command) {
            'I' -> insert(value)
            'D' -> remove(value == 1)
        }
    }
    builder.apply {
        if (map.isEmpty()) appendLine("EMPTY")
        else append(map.lastKey()).append(' ').appendLine(map.firstKey())
    }
}

fun insert(value: Int) {
    map[value] = (map[value] ?: 0) + 1
}

fun remove(isTargetMax: Boolean) {
    if (map.isEmpty()) return

    val (target, count) = if (isTargetMax) map.lastEntry() else map.firstEntry()

    if (count > 1) map[target] = map[target]!! - 1
    else map.remove(target)
}

fun main() = with(reader) {
    repeat(readLine().toInt()) {
        input()
        solve()
    }
    println(builder)
}