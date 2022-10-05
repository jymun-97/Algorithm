class Solution {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>) = Array(arr1.size) { row ->
        IntArray(arr2[0].size) { col ->
            arr1[row].foldIndexed(0) { idx, acc, i ->
                acc + i * arr2[idx][col]
            }
        }
    }
}