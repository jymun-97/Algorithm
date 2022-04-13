import java.util.*

class Solution {

    val map = hashMapOf<String, String>()
    fun solution(record: Array<String>): Array<String> {
        record.forEach {
            StringTokenizer(it).apply {
                val action = nextToken()
                if (action == "Leave") return@forEach

                val id = nextToken()
                val nickname = nextToken()

                map[id] = nickname
            }
        }

        var ans = arrayListOf<String>()
        record.forEachIndexed { index, s ->
            val st = StringTokenizer(s)
            ans.add(
                when (st.nextToken()) {
                    "Enter" -> "${map[st.nextToken()]}님이 들어왔습니다."

                    "Leave" -> "${map[st.nextToken()]}님이 나갔습니다."

                    else -> return@forEachIndexed
                }
            )
        }
        return ans.toTypedArray()
    }
}

fun main() {
    val s = Solution()
    val result = s.solution(
        arrayOf(
            "Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"
        )
    )
    println(result.contentToString())
}