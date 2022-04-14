import java.lang.StringBuilder
import java.util.HashMap

class Node {
    var children: HashMap<Char, Node>
    var count: Int

    init {
        children = HashMap()
        count = 0
    }
}

class Trie {
    var root: Node
    fun insert(str: String) {
        var node: Node? = root
        for (c in str.toCharArray()) {
            node!!.count++
            if (!node.children.containsKey(c)) node.children[c] = Node()
            node = node.children[c]
        }
    }

    fun getCount(str: String): Int {
        var node: Node? = root
        for (c in str.toCharArray()) {
            if (c == '?') break
            if (!node!!.children.containsKey(c)) return 0
            node = node.children[c]
        }
        return node!!.count
    }

    init {
        root = Node()
    }
}

class Solution {
    fun solution(words: Array<String>, queries: Array<String>): IntArray {
        val answer = IntArray(queries.size)
        val tries = arrayOfNulls<Trie>(100001)
        val reverse_tries = arrayOfNulls<Trie>(100001)

        for (i in 0..100000) {
            tries[i] = Trie()
            reverse_tries[i] = Trie()
        }
        for (word in words) {
            val n = word.length

            tries[n]!!.insert(word)
            reverse_tries[n]!!.insert(StringBuilder(word).reverse().toString())
        }
        for (i in queries.indices) {
            var query = queries[i]
            val n = query.length

            if (query[0] == '?') {
                query = StringBuilder(query).reverse().toString()
                answer[i] = reverse_tries[n]!!.getCount(query)
            } else answer[i] = tries[n]!!.getCount(query)
        }

        return answer
    }
}