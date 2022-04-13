
class Solution {

    var row = 0
    var col = 0
    lateinit var data: Array<Array<String>>

    val keys = arrayListOf<List<Int>>()

    fun solution(relation: Array<Array<String>>): Int {
        row = relation.size
        col = relation[0].size
        data = relation

        for (size in 1 .. col) {
            dfs(0, size, -1, mutableListOf())
        }

        return keys.size
    }

    fun dfs(k: Int, n: Int, pre: Int, cand: MutableList<Int>) {
        if (k == n) {
            keys.forEach { key ->
                if (cand.containsAll(key)) return
            }
            if (check(cand)) keys.add(cand.toList())
            return
        }

        for (i in pre + 1 until col) {
            cand.add(i)
            dfs(k + 1, n, i, cand)
            cand.remove(i)
        }
    }

    fun check(key: List<Int>) : Boolean {
        val set = hashSetOf<String>()

        for (i in 0 until row) {
            val sb = StringBuilder()
            key.forEach { j ->
                sb.append(data[i][j]).append(' ')
            }
            set.add(sb.toString())
        }

        return set.size == row
    }
}

fun main() {
    val s = Solution()
    val relation = arrayOf(
        arrayOf("100","ryan","music","2"),
        arrayOf("200","apeach","math","2"),
        arrayOf("300","tube","computer","3"),
        arrayOf("400","con","computer","4"),
        arrayOf("500","muzi","music","3"),
        arrayOf("600","apeach","music","2")
    )

    println(s.solution(relation))
}