package days

@AdventOfCodePuzzle(
    name = "Guard Gallivant",
    url = "https://adventofcode.com/2024/day/6",
    date = Date(day = 6, year = 2024)
)
class Day6(private val input: List<String>) : Puzzle {

    private val obstacles: List<Point> = findSymbols('#')
    private val start: Pair<Point, Direction> = findSymbols('^').first() to Direction.NORTH

    override fun partOne(): Int = findPath(obstacles).map { it.first }.toSet().size

    override fun partTwo(): Int {
        val tryObstacles: Set<Point> = findPath(obstacles).map { it.first }.toSet() - start.first
        println("tryObstacles = ${tryObstacles.count()}")
        println("start = ${start}")
        draw(tryObstacles.toList(), 0..input.size, 0..input[0].length)

        return tryObstacles
            .map { o -> findPath(obstacles + o).size }
            .count { it == 0 }
    }

    private fun findPath(obstacles: List<Point>): List<Pair<Point, Direction>> {
        val path = mutableListOf<Pair<Point, Direction>>()

        var guard: Pair<Point, Direction> = start
        while (guard.first.onGrid()) {
            path.add(guard)

            val peek = guard.first + guard.second.move
            if (peek in obstacles) guard = guard.first to guard.second.turnRight()

            val next = guard.first + guard.second.move
            guard = next to guard.second
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
