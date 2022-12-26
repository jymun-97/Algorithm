data class Dragon(
    val id: Int,
    val power: Int
)
var n = 0
var m = 0
lateinit var dragons: List<Dragon>

fun input() = with(System.`in`.bufferedReader()) {
    readLine().split(" ").map { it.toInt() }.apply {
        n = this[0]
        m = this[1]
    }
    dragons = readLine()
        .split(" ")
        .mapIndexed { id, power -> Dragon(id + 1, power.toInt()) }
        .sortedBy { it.power }
}

fun solve() {
    for (i in 1 until n) {
        val diff = dragons[i].power - dragons[i - 1].power
        if (diff > m) {
            print("NO")
            return
        }
    }
    print(
        buildString {
            dragons.forEach { append(it.id).append(' ') }
        }
    )
}

fun main() {
    input()
    solve()
}