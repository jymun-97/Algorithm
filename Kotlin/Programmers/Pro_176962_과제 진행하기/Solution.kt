import java.util.LinkedList
import java.util.PriorityQueue

data class Task(
    val name: String,
    val start: Int,
    val playTime: Int
) {
    var remain = playTime
}

class Solution {
    var n = 0
    val readyQueue = PriorityQueue<Task>() { t1, t2 -> t1.start - t2.start }
    val readyStack = LinkedList<Task>()

    fun solution(plans: Array<Array<String>>): Array<String> {
        init(plans)

        val ans = LinkedList<String>()
        var task = readyQueue.poll()
        var time = task.start

        while (true) {
            val expected = time + task.remain
            when {
                readyQueue.isNotEmpty() && expected >= readyQueue.peek().start -> {
                    val nextTask = readyQueue.poll()
                    task.remain -= nextTask.start - time

                    if (task.remain == 0) ans.add(task.name)
                    else readyStack.add(task)

                    task = nextTask
                    time = task.start
                }
                readyStack.isNotEmpty() -> {
                    ans.add(task.name)

                    task = readyStack.pollLast()
                    time = expected
                }
                readyQueue.isNotEmpty() -> {
                    ans.add(task.name)

                    task = readyQueue.poll()
                    time = task.start
                }
                else -> break
            }
        }
        ans.add(task.name)

        return ans.toTypedArray()
    }

    fun init(plans: Array<Array<String>>) {
        readyQueue.addAll(
            plans.map {
                val (name, timestamp, playTimeStr) = it
                Task(
                    name = name,
                    start = timestampToMinutes(timestamp),
                    playTime = playTimeStr.toInt()
                )
            }
        )
    }

    fun timestampToMinutes(timestamp: String): Int {
        val (hour, minute) = timestamp.split(":").map { it.toInt() }
        return hour * 60 + minute
    }
}