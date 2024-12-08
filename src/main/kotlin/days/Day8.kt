package days


@AdventOfCodePuzzle(
    name = "Resonant Collinearity",
    url = "https://adventofcode.com/2024/day/8",
    date = Date(day = 8, year = 2024)
)
class Day8(val input: List<String>) : Puzzle {

    val sym = find('.').groupBy({ it.first }, { it.second })

    private fun find(symbol: Char) = input
        .flatMapIndexed { y, line -> line.mapIndexedNotNull() { x, c -> if (c != symbol) c to Point(x, y) else null } }

    override fun partOne(): Int {
        val antidodes = sym.flatMap { (symbol, locations) ->
            println("symbol = ${symbol}, locations = ${locations}")
            val points = locations.flatMap { loc ->
                val points = locations - loc
                val antenna = points.map { loc + ((it - loc) * 2) }
                //println("  loc = ${loc} -> $points -> $antenna")
                antenna
            }
            points
        }
            .filter { it.x in input[0].indices && it.y in input.indices }
            .toSet()
        return antidodes.size
    }

    override fun partTwo() = 0

    data class Point(val x: Int, val y: Int) {

        operator fun plus(other: Point) = Point(x + other.x, y + other.y)
        operator fun minus(other: Point) = Point(x - other.x, y - other.y)
        operator fun times(o: Int) = Point(x * o, y * o)
    }

}