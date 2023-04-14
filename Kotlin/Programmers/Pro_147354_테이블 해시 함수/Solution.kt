class Solution {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
        return data.sortedWith(compareBy({ it[col - 1] }, { -it[0] }))
            .mapIndexed { i, tuple -> tuple.sumOf { it % (i + 1) } }
            .filterIndexed { i, _ -> i + 1 in row_begin..row_end }
            .reduce { acc, value -> acc xor value }
    }
}