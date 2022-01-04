class Solution {
    val count = hashMapOf<String, Int>()
    val targets = hashSetOf<Int>()

    fun solution(orders: Array<String>, course: IntArray): Array<String> {
        targets.addAll(course.asList())

        orders.forEach { order ->
            dfs(
                order.toCharArray().sorted().toCharArray().concatToString(),
                "",
                0,
                hashSetOf()
            )
        }

        val maxOrder = hashMapOf<Int, ArrayList<String>>()
        count.forEach { (key, value) ->
            if (value < 2) return@forEach

            val size = key.length
            if (maxOrder[size].isNullOrEmpty()) {
                maxOrder[size] = arrayListOf(key)
                return@forEach
            }

            val target = maxOrder[size]!![0]
            when {
                value > count[target]!! -> maxOrder[size] = arrayListOf(key)

                value == count[target]!! -> maxOrder[size]!!.add(key)
            }
        }

        val answer = arrayListOf<String>()
        maxOrder.values.forEach { list ->
            answer.addAll(list)
        }

        return answer.sorted().toTypedArray()
    }

    fun dfs(order: String, combi: String, k: Int, visit: HashSet<String>) {
        val size = combi.length
        if (!visit.contains(combi) && targets.contains(size)) {
            count[combi] = (count[combi] ?: 0) + 1
            visit.add(combi)
        }

        if (k >= order.length) return

        dfs(
            order,
            StringBuilder(combi).append(order[k]).toString(),
            k + 1,
            visit
        )
        dfs(order, combi, k + 1, visit)
    }
}