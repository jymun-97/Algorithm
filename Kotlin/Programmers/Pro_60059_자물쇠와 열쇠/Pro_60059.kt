
class Solution {
    var n = 0
    var start = 0
    var end = 0
    var keySize = 0
    var lockSize = 0

    lateinit var map : Array<IntArray>
    lateinit var key : Array<IntArray>

    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        keySize = key.size
        lockSize = lock.size
        start = keySize - 1
        end = start + lockSize - 1
        this.key = key

        n = lockSize + 2 * (keySize - 1)
        map = Array(n) { IntArray(n) }

        for (i in start .. end) {
            for (j in start .. end) {
                map[i][j] = lock[i - start][j - start]
            }
        }
        return run()
    }

    fun run() : Boolean {
        for (i in 0 until 4) {
            if (check()) return true
            rotate()
        }
        return false
    }

    fun check() : Boolean {
        for (i in 0 .. end) {
            for (j in 0 .. end) {
                if (isFinish(i, j)) return true
            }
        }
        return false
    }

    fun isFinish(sr: Int, sc: Int) : Boolean {
        val copy = Array(n) { IntArray(n) }
        map.forEachIndexed { index, ints -> copy[index] = ints.clone() }

        for (i in sr until sr + keySize) {
            for (j in sc until sc + keySize) {
                copy[i][j] += key[i - sr][j - sc]
            }
        }

        for (i in start .. end) {
            for (j in start .. end) {
                if (copy[i][j] != 1) return false
            }
        }

        return true
    }

    fun rotate() {
        val newKey = Array(keySize) { IntArray(keySize) }

        for (i in 0 until keySize) {
            for (j in 0 until keySize) {
                newKey[i][j] = key[j][keySize - i - 1]
            }
        }
        key = newKey
    }
}

fun main() {
    println(
        Solution().solution(
            arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(1, 0, 0),
                intArrayOf(0, 1, 1)
            ),
            arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(1, 0, 1)
            )
        )
    )
}