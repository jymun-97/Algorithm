
class Solution {
    fun solution(p: String): String {
        return run(
            StringBuilder(p)
        ).toString()
    }

    fun run(w: StringBuilder) : StringBuilder {
        if (w.isEmpty() || isValid(w)) return w

        val tokens = split(w)
        var u = StringBuilder(tokens[0])
        var v = StringBuilder(tokens[1])

        v = run(v)
        return if (!isValid(u)) {
            u = reverse(StringBuilder(u.substring(1, u.length - 1)))
            v.insert(0, '(').append(')')

            v.append(u)
        } else u.append(v)
    }

    fun reverse(str: StringBuilder) : StringBuilder {
        val copy = StringBuilder()
        str.forEach { c -> copy.append(
            if (c == '(') ')'
            else '('
        ) }

        return copy
    }

    fun isValid(str: StringBuilder) : Boolean {
        var open = 0
        var close = 0
        str.forEach { c ->
            if (c == '(') open++
            else {
                close++
                if (open < close) return false
            }
        }
        return true
    }

    fun split(str: StringBuilder): Array<String> {
        val n = str.length
        val count = intArrayOf(0, 0)

        var target = -1
        for (i in 0 until n) {
            count[if (str[i] == '(') 0 else 1]++
            if (count[0] == count[1]) {
                target = i
                break
            }
        }
        return arrayOf(
            str.substring(0, target + 1),
            str.substring(target + 1)
        )
    }
}

fun main() {
    println(
        Solution().solution(
            "()))((()"
        )
    )
}