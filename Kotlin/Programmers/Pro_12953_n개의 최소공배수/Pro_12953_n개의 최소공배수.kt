class Solution {
    fun solution(arr: IntArray): Int = arr.fold(1) { acc, i -> lcm(acc, i)}

    fun gcd(a: Int, b: Int) : Int = if (b == 0) a else gcd(b, a % b)

    fun lcm(a: Int, b: Int) : Int = a * b / gcd(a, b)
}