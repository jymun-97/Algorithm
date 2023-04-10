class Solution {
    var cap = 0
    var n = 0
    var ans = 0L
    lateinit var deliveryArray: IntArray
    lateinit var pickupArray: IntArray

    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        this.cap = cap
        this.n = n + 1
        this.deliveryArray = intArrayOf(0).plus(deliveries)
        this.pickupArray = intArrayOf(0).plus(pickups)

        for (target in n downTo 1) {
            if (deliveryArray[target] <= 0 && pickupArray[target] <= 0) continue

            val max = maxOf(deliveryArray[target], pickupArray[target])
            val count = max / cap + if (max % cap == 0) 0 else 1

            spread(target, deliveryArray, count * cap)
            spread(target, pickupArray, count * cap)

            ans += target * count * 2
        }

        return ans
    }

    fun spread(target: Int, arr: IntArray, limit: Int) {
        var remain = if (arr[target] == 0) limit else limit - arr[target]
        arr[target] = 0

        for (i in target - 1 downTo 1) {
            if (remain <= 0) break
            if (arr[i] >= remain) {
                arr[i] -= remain
                break
            }
            remain -= arr[i]
            arr[i] = 0
        }
    }
}

fun main() {
    println(
        Solution().solution(
            cap = 4,
            n = 4,
            deliveries = intArrayOf(25,24,51,0),
            pickups = intArrayOf(51,0,0,49)
        )
    )
}