import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import java.util.TreeMap

const val MAX = 1000000000

var n = 0
var m = 0
var k = 0
var map = TreeMap<Int, Int>()
var commands = arrayListOf<Array<Int>>()
var flag = true
var sb = StringBuilder()

fun input() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    n = st.nextToken().toInt()
    m = st.nextToken().toInt()
    k = st.nextToken().toInt()

    for (i in 1..n) {
        st = StringTokenizer(readLine())
        var key = st.nextToken().toInt()
        var value = st.nextToken().toInt()

        map[key] = value
    }

    while (m-- > 0) {
        st = StringTokenizer(readLine())

        var command = st.nextToken().toInt()
        var key = st.nextToken().toInt()
        var value = if (st.hasMoreTokens()) st.nextToken().toInt() else -1

        commands.add(arrayOf(command, key, value))
    }
}

fun solve() {
    commands.forEach { arr ->
        when (arr[0]) {
            1 -> add(
                key = arr[1],
                value = arr[2]
            )
            2 -> update(
                key = findKey(arr[1]),
                newValue = arr[2]
            )
            3 -> printValueOf(
                key = findKey(arr[1])
            )
        }
    }
    print(sb)
}

fun add(key: Int, value: Int) {
    map[key] = value
    flag = true
}

fun update(key: Int, newValue: Int) {
    when {
        key < 0 -> return
        else -> map[key] = newValue
    }
}

fun printValueOf(key: Int) {
    when (key) {
        -1 -> sb.append(-1)
        -2 -> sb.append('?')
        else -> sb.append(map[key])
    }
    sb.append('\n')
}

fun findKey(input: Int): Int {
    if (map.containsKey(input)) return input

    var leftKey = map.lowerKey(input)?.let { key ->
        if (input - key <= k) key
        else null
    }
    var rightKey = map.higherKey(input)?.let { key ->
        if (key - input <= k) key
        else null
    }

    if (leftKey == null && rightKey == null) return -1
    if (leftKey != null && rightKey != null) {
        return when {
            leftKey + rightKey == input * 2 -> -2

            input - leftKey < rightKey - input -> leftKey
            else -> rightKey
        }
    }
    leftKey?.let { return leftKey }
    rightKey?.let { return rightKey }

    return -1
}

fun main() {
    input()
    solve()
}