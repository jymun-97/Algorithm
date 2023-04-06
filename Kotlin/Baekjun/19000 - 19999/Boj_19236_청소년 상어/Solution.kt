data class Point(
    val row: Int,
    val col: Int,
) {
    fun moved(dir: Directions): Point? {
        val nr = row + dir.row
        val nc = col + dir.col

        return if (nr !in 0 until 4 || nc !in 0 until 4) null else Point(nr, nc)
    }
}

data class Fish(
    val id: Int,
    val dir: Directions,
    val loc: Point,
) {
    fun moved(sharkLoc: Point): Fish {
        var dir = dir
        repeat(8) {
            loc.moved(dir)?.let {
                if (it != sharkLoc) return Fish(id, dir, it)
                dir = dir.next
            } ?: run {
                dir = dir.next
            }
        }
        return this
    }
}

data class Shark(
    val dir: Directions,
    val loc: Point,
) {
    fun moved(
        checkFishExist: (Point) -> Fish?,
    ): List<Shark> {
        val result = mutableListOf<Shark>()
        var loc = loc.moved(dir)

        while (loc != null) {
            checkFishExist(loc)?.let {
                result.add(Shark(it.dir, loc!!))
                null
            } ?: run {
                loc = loc!!.moved(dir)
            }
        }

        return result
    }
}

enum class Directions(
    val row: Int,
    val col: Int,
) {
    UP(-1, 0), LEFT_UP(-1, -1), LEFT(0, -1), LEFT_DOWN(1, -1),
    DOWN(1, 0), RIGHT_DOWN(1, 1), RIGHT(0, 1), RIGHT_UP(-1, 1);

    val next: Directions
        get() = values()[if (ordinal == 7) 0 else ordinal + 1]
}

var ans = 0
lateinit var originBoard: HashMap<Point, Fish?>

fun input() = with(System.`in`.bufferedReader()) {
    originBoard = hashMapOf()
    repeat(4) { row ->
        val tokens = readLine().split(" ").map { it.toInt() }
        for (i in 0 until 8 step 2) {
            val id = tokens[i]
            val dir = Directions.values()[tokens[i + 1] - 1]
            val loc = Point(row, i / 2)

            originBoard[loc] = Fish(id, dir, loc)
        }
    }
}

fun solve() {
    val targetLoc = Point(0, 0)
    val target = originBoard[targetLoc]!!

    originBoard[targetLoc] = null
    dfs(
        Shark(target.dir, targetLoc),
        target.id,
        originBoard
    )

    println(ans)
}

fun dfs(shark: Shark, sum: Int, preBoard: HashMap<Point, Fish?>) {
    ans = maxOf(ans, sum)

    val board = HashMap(preBoard)
    for (id in 1..16) {
        val from = board.values.find { it?.id == id } ?: continue
        val to = from.moved(shark.loc)
        board[to.loc] = to.also {
            board[from.loc] = board[to.loc]?.let {
                Fish(it.id, it.dir, from.loc)
            }
        }
    }

    shark.moved { board[it] }.forEach {
        val target = board[it.loc]!!

        board[it.loc] = null
        dfs(it, sum + target.id, board)
        board[it.loc] = target
        dfs(it, sum, board)
    }
}

fun printBoard(shark: Shark, board: HashMap<Point, Fish?>) {
    println(
        buildString {
            for (row in 0 until 4) {
                for (col in 0 until 4) {
                    val point = Point(row, col)
                    val fish = board[point]

                    append(if (point == shark.loc) '*' else fish?.id ?: 0).append(' ')
                }
                appendLine()
            }
        }
    )
}

fun main() {
    input()
    solve()
}