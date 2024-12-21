package days

@AdventOfCodePuzzle(
    name = "Hoof It",
    url = "https://adventofcode.com/2024/day/10",
    date = Date(day = 10, year = 2024)
)
class Day10(val input: List<String>) : Puzzle {

    val sym: Map<Point, Int> = findAllIgnoring().toMap()

    override fun partOne(): Int {
        val starts = sym.filter { (_, v) -> v == 0 }.keys
        return starts.sumOf(::findPath)
    }

    private fun findPath(start: Point): Int {
        println("start = $start")
        return 1
    }

    override fun partTwo() = 0

    private fun findAllIgnoring(): List<Pair<Point, Int>> = input
        .flatMapIndexed { y, line -> line.mapIndexedNotNull() { x, c -> if (c.isDigit()) Point(x, y) to c.digitToInt() else null } }

}