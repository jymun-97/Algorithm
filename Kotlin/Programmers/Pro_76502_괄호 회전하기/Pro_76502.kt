// https://programmers.co.kr/learn/courses/30/lessons/76502
import java.util.Stack

class Solution {

    val open = hashSetOf('(', '{', '[')
    val visit = hashMapOf<String, Boolean>()
    var length = 0

    fun solution(s: String): Int {
        length = s.length
        if (length % 2 == 1) return 0

        var str = s
        for (i in str.indices) {
            if (!visit.containsKey(str)) check(str)
            str = rotate(str)
        }

        var answer = 0
        visit.forEach { (_, value) -> if (value) answer++ }
        return answer
    }

    fun rotate(s: String) = s.substring(1, length) + s[0]

    fun check(s: String) {
        val stack = Stack<Char>()

        for (i in 0 until length) {
            if (stack.isEmpty()) {
                if (!open.contains(s[i])) {
                    visit[s] = false
                    break
                }
                stack.push(s[i])
            }
            else {
                if (isMatch(stack.peek(), s[i])) stack.pop()
                else stack.push(s[i])
            }
        }

        if (!visit.containsKey(s)) visit[s] = stack.isEmpty()
    }

    fun isMatch(left: Char, right: Char) : Boolean {
        return (left == '(' && right == ')') ||
                (left == '{' && right == '}') ||
                (left == '[' && right == ']')
    }
}