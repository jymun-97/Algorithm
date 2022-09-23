class Solution {
    var answer = intArrayOf(0, 0)
    fun solution(s: String): IntArray {
        var str = s

        while (str != "1") {
            answer[1] += str.count { it == '0' }
            str = convert(str)
        }

        return answer
    }

    fun convert(str: String) = str.replace("0", "").length.toString(2).also { answer[0]++ }
}