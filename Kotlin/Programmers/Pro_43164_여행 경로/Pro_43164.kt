import java.util.LinkedList
import java.util.TreeSet

class Solution {
    data class Ticket(
        val from: String,
        val to: String
    )
    lateinit var count: HashMap<Ticket, Int>
    lateinit var graph: HashMap<String, TreeSet<String>>
    lateinit var answer: LinkedList<String>

    var n = 0
    var flag = false

    fun solution(tickets: Array<Array<String>>): Array<String> {
        count = hashMapOf()
        graph = hashMapOf()
        answer = LinkedList()
        n = tickets.size

        tickets.forEach { info ->
            val from = info[0]
            val to = info[1]
            val ticket = Ticket(from, to)

            count[ticket] = (count[ticket] ?: 0) + 1
            if (!graph.containsKey(from)) graph[from] = TreeSet()

            graph[from]!!.add(to)
        }

        answer.add("ICN")
        dfs("ICN", 0)

        return answer.toTypedArray()
    }

    fun dfs(from: String, k: Int) {
        if (k == n) {
            flag = true
            return
        }
        graph[from]?.let {
            it.forEach { to ->
                val ticket = Ticket(from, to)
                if (count[ticket]!! > 0) {
                    count[ticket] = count[ticket]!! - 1
                    answer.addLast(to)

                    dfs(to, k + 1)
                    if (flag) return

                    count[ticket] = count[ticket]!! + 1
                    answer.removeLast()
                }
            }
        }
    }
}