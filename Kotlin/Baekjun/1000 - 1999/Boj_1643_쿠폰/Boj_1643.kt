import kotlin.math.max

var nums = mutableListOf<Int>()
val ans = StringBuilder()

fun input() = with(System.`in`.bufferedReader()) {
    var str: String?
    while (!readLine().also { str = it }.isNullOrEmpty()) {
        nums.add(str!!.toInt())
    }
}

fun solve() {
    nums.forEach { n ->
        var denom = n.toLong()
        var numer = n.toLong()

        for (x in 1 until n - 1) {
            numer = denom * n + numer * (n - x)
            denom *= (n - x)

            val gcd = gcd(denom, numer)
            denom /= gcd
            numer /= gcd
        }

        print(n, denom, numer)
    }
    println(ans)
}

fun gcd(a: Long, b: Long): Long = if (b != 0L) gcd(b, a % b) else a

fun print(n: Int, denom: Long, numer: Long) {
    if (numer % denom == 0L) {
        ans.append(
            if (n == 1) 1 else numer / denom + n
        ).append('\n')
    } else {
        val num = numer / denom + n
        val blanks = (0..num.toString().length).joinToString("") { " " }
        val hyphens = (0 until max(numer % denom, denom).toString().length).joinToString("") { "-" }

        ans.append(blanks)
            .append(numer % denom)
            .append('\n')
            .append(num)
            .append(' ')
            .append(hyphens)
            .append('\n')
            .append(blanks)
            .append(denom)
            .append('\n')
    }
}

fun main() {
    input()
    solve()
}
