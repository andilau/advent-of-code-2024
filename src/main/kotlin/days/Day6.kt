package days

@AdventOfCodePuzzle(
    name = "Guard Gallivant",
    url = "https://adventofcode.com/2024/day/6",
    date = Date(day = 6, year = 2024)
)
class Day6(private val input: List<String>) : Puzzle {

    private val obstacles: List<Point> = findSymbols('#')
    private val start: Pair<Point, Direction> = findSymbols('^').first() to Direction.NORTH

    override fun partOne(): Int = obstacles.findPath().map { it.first }.toSet().size

    override fun partTwo(): Int {
        val guardPath: Set<Point> = obstacles.findPath().map { it.first }.toSet() - start.first
        return guardPath
            .count { obstacle -> (obstacles + obstacle).findPath().isEmpty() }
    }

    private fun List<Point>.findPath(): List<Pair<Point, Direction>> {
        val path = mutableListOf<Pair<Point, Direction>>()

        var guard: Pair<Point, Direction> = start
        while (guard.first.onGrid()) {
            path.add(guard)

            val next = guard.first + guard.second.move
            guard = if (next in this) {
                guard.first to guard.second.turnRight()
            } else
                next to guard.second
            if (guard in path) return emptyList()
        }
        return path
    }

    private fun findSymbols(symbol: Char) =
        input.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                if (char == symbol) Point(x, y) else null
            }
        }

    private fun Point.onGrid(): Boolean =
        x in input[0].indices && y in input.indices

}
