import java.io.*
import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

var n = 0
var k = 0

lateinit var w : IntArray
lateinit var info : HashMap<Int, LinkedList<Int>>
lateinit var set : HashSet<Int>

fun input() = with(System.`in`.bufferedReader()) {
    StringTokenizer(readLine()).apply {
        n = nextToken().toInt()
        k = nextToken().toInt()
    }

    w = IntArray(k + 1)
    info = hashMapOf()
    set = hashSetOf()

    StringTokenizer(readLine()).apply {
        for (i in 1 .. k) {
            val target = nextToken().toInt()

            if (!info.containsKey(target)) info[target] = LinkedList()
            info[target]!!.add(i)
            w[i] = target
        }
    }
}

fun solve() {
    var answer = 0
    for (i in 1 .. k) {
        val target = w[i]
        if (set.contains(target)) {
            info[target]!!.poll()
            continue
        }

        if (set.size == n) {
            var removeOne = find()
            set.remove(removeOne)
            answer++
        }
        set.add(target)
        info[target]!!.poll()
//        println("$i : ${set}")
    }
    println(answer)
}

fun find() : Int {
    var target = 0
    set.forEach { key ->
        if (info[key]!!.isEmpty()) return key
        if (target == 0) {
            target = key
        }
        else {
            if (info[target]!!.peek() < info[key]!!.peek()) {
                target = key
            }
        }
    }
    return target
}

fun main() {
    input()
    solve()
}