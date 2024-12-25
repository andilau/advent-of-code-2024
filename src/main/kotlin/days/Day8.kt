package days


@AdventOfCodePuzzle(
    name = "Resonant Collinearity",
    url = "https://adventofcode.com/2024/day/8",
    date = Date(day = 8, year = 2024)
)
class Day8(val input: List<String>) : Puzzle {

    val sym = findAllIgnoring('.').groupBy({ it.first }, { it.second })

    override fun partOne() = sym.values
        .flatMap { antennas -> firstAntinodes(antennas) }
        .filter { inBounds(it) }
        .toSet()
        .size

    override fun partTwo() = sym.values
        .flatMap { antennas -> allAntinodes(antennas) }
        .toSet()
        .size

    private fun findAllIgnoring(symbol: Char) = input
        .flatMapIndexed { y, line -> line.mapIndexedNotNull() { x, c -> if (c != symbol) c to Point(x, y) else null } }

    private fun inBounds(point: Point): Boolean = point.x in input[0].indices && point.y in input.indices

    private fun firstAntinodes(antennas: List<Point>): List<Point> =
        antennas.flatMap { antenna ->
            (antennas - antenna).map { antenna + ((it - antenna) * 2) }
        }

    private fun allAntinodes(antennas: List<Point>): List<Point> =
        antennas.flatMap { antenna ->
            (antennas - antenna).flatMap { antinodesOf(antenna, it) }
        }

    private fun antinodesOf(antenna: Point, other: Point) = generateSequence<Point>(antenna) {
        val next = it + (other - antenna)
        if (inBounds(next)) next else null
    }

}