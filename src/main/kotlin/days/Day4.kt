package days

@AdventOfCodePuzzle(
    name = "Ceres Search",
    url = "https://adventofcode.com/2024/day/4",
    date = Date(day = 4, year = 2024)
)
class Day4(val memory: List<String>) : Puzzle {

    override fun partOne() = find('X').sumOf(::countXmas)

    override fun partTwo() = find('A').count(::isXmas)

    private fun isXmas(pos: Point): Boolean = listOf(
        getChar(pos.x - 1, pos.y - 1),
        getChar(pos.x + 1, pos.y - 1),
        getChar(pos.x + 1, pos.y + 1),
        getChar(pos.x - 1, pos.y + 1)
    )
        .joinToString("") in setOf("MMSS", "SMMS", "SSMM", "MSSM")

    private fun find(symbol: Char) = memory
        .flatMapIndexed { y, line -> line.mapIndexedNotNull() { x, c -> if (c == symbol) Point(x, y) else null } }

    private fun countXmas(p: Point): Int {
        return sequenceOf(
            listOf(Point(1, 0), Point(2, 0), Point(3, 0)),
            listOf(Point(1, 1), Point(2, 2), Point(3, 3)),
            listOf(Point(0, 1), Point(0, 2), Point(0, 3)),
            listOf(Point(-1, 1), Point(-2, 2), Point(-3, 3)),
            listOf(Point(-1, 0), Point(-2, 0), Point(-3, 0)),
            listOf(Point(-1, -1), Point(-2, -2), Point(-3, -3)),
            listOf(Point(0, -1), Point(0, -2), Point(0, -3)),
            listOf(Point(1, -1), Point(2, -2), Point(3, -3))
        )
            .map { it.map { delta -> getChar(delta.x + p.x, delta.y + p.y) }.joinToString("") }
            .filter { it == "MAS" }
            .count()
    }

    private fun getChar(x: Int, y: Int): Char {
        if (y !in 0..memory.lastIndex || x !in 0..memory[0].lastIndex) return ' '
        return memory[y][x]
    }

    data class Point(val x: Int, val y: Int)
}