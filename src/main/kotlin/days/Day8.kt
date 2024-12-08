package days


@AdventOfCodePuzzle(
    name = "Resonant Collinearity",
    url = "https://adventofcode.com/2024/day/8",
    date = Date(day = 8, year = 2024)
)
class Day8(val input: List<String>) : Puzzle {

    val sym = findAllIgnoring('.').groupBy({ it.first }, { it.second })

    override fun partOne() = sym.values
        .flatMap { antennas -> antinodes(antennas) }
        .filter { inBounds(it) }
        .toSet()
        .size

    override fun partTwo() = sym.values
        .flatMap { antennas -> antinodes2(antennas) }
        .filter { inBounds(it) }
        .toSet()
        .size

    private fun findAllIgnoring(symbol: Char) = input
        .flatMapIndexed { y, line -> line.mapIndexedNotNull() { x, c -> if (c != symbol) c to Point(x, y) else null } }

    private fun inBounds(point: Point): Boolean = point.x in input[0].indices && point.y in input.indices

    private fun antinodes(antennas: List<Point>): List<Point> =
        antennas.flatMap { antenna ->
            (antennas - antenna).map { antenna + ((it - antenna) * 2) }
        }

    private fun antinodes2(antennas: List<Point>): List<Point> =
        antennas.flatMap { antenna ->
            (antennas - antenna).flatMap { point(antenna, it) }
        }

    private fun point(antenna: Point, point: Point) = generateSequence<Point>(antenna) {
        val next = it + (point - antenna)
        if (inBounds(next)) next else null
    }

    data class Point(val x: Int, val y: Int) {

        operator fun plus(other: Point) = Point(x + other.x, y + other.y)
        operator fun minus(other: Point) = Point(x - other.x, y - other.y)
        operator fun times(o: Int) = Point(x * o, y * o)
    }

}