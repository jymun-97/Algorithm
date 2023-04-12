class Solution {
    var min = Int.MAX_VALUE

    fun solution(a: IntArray): Int = with(a.toList()) {
        val target = indexOf(minOf { it })

        return subList(0, target).fold(0) { acc, i ->
            min = minOf(min, i).also { println(it) }
            if (i == min) acc + 1 else acc
        }.also { min = Int.MAX_VALUE } + subList(target, size).foldRight(0) { i, acc ->
            min = minOf(min, i).also { println(it) }
            if (i == min) acc + 1 else acc
        }
    }
}