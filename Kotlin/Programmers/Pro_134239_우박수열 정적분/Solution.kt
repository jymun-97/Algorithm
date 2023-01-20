class Solution {
    val sequence = mutableListOf<Int>()
    lateinit var area: DoubleArray

    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
        makeSequence(k)
        area = DoubleArray(sequence.lastIndex) {
            val high = maxOf(sequence[it], sequence[it + 1])
            val low = minOf(sequence[it], sequence[it + 1])

            (high - low) / 2.0 + low
        }

        return ranges.map {
            val from = it[0]
            val to = sequence.lastIndex + it[1]

            getArea(from, to)
        }.toDoubleArray()
    }

    fun makeSequence(num: Int){
        sequence.add(num)
        when {
            num == 1 -> {}

            num % 2 == 0 -> makeSequence(num / 2)

            else -> makeSequence(num * 3 + 1)
        }
    }

    fun getArea(from: Int, to: Int): Double = when {
        from == to -> 0.0

        from > to -> -1.0

        else -> (from until to).sumOf { area[it] }
    }
}