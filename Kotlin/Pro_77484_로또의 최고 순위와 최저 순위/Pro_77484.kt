import java.util.HashSet

internal class Solution {
    fun solution(lottos: IntArray, win_nums: IntArray): IntArray {
        val win = HashSet<Int>()
        for (num in win_nums) win.add(num)
        var hit = 0
        var count = 0
        for (num in lottos) {
            if (num == 0) count++
            if (win.contains(num)) hit++
        }
        if (hit == 0) hit++
        val highest = if (count == 6) 1 else 7 - hit - count
        val lowest = 7 - hit
        return intArrayOf(highest, lowest)
    }
}