package days

@AdventOfCodePuzzle(
    name = "Guard Gallivant",
    url = "https://adventofcode.com/2024/day/6",
    date = Date(day = 6, year = 2024)
)
class Day6(private val input: List<String>) : Puzzle {

    private val obstacles: List<Point> = pairs('#')
    private val start: Pair<Point, Direction> = pairs('^').first() to Direction.NORTH

    private

    fun pairs(symbol: Char) =
        input.flatMapIndexed { y, line ->
            line.mapIndexedNotNull { x, char ->
                if (char == symbol) Point(x, y) else null
            }
        }

    private fun onGrid(pos: Point): Boolean =
        pos.x in 0..input[0].lastIndex && pos.y in 0..input.lastIndex

    override fun partOne(): Int = findPath(obstacles).map { it.first }.toSet().size

    override fun partTwo(): Int {
        val tryObstacles: Set<Point> = findPath(obstacles).map { it.first }.toSet() - start.first
        return tryObstacles
            .map { o -> findPath(obstacles + o) }
            .count { it.isEmpty() }
    }

    private fun findPath(obstacles: List<Point>): List<Pair<Point, Direction>> {
        val path = mutableListOf<Pair<Point, Direction>>()
        var pos: Pair<Point, Direction> = start
        while (onGrid(pos.first)) {
            if (pos in path) return emptyList()
            path.add(pos)

            val peek = pos.first + pos.second.move
            if (peek in obstacles) pos = pos.first to pos.second.turnRight()

            val next = pos.first + pos.second.move
            pos = next to pos.second
        }
        return path
    }

    data class Point(val x: Int, val y: Int) {

        operator fun plus(move: Point) = Point(x + move.x, y + move.y)
    }

    enum class Direction(val move: Point) {
        NORTH(Point(0, -1)),
        SOUTH(Point(0, 1)),
        EAST(Point(1, 0)),
        WEST(Point(-1, 0));

        fun turnRight(): Direction {
            return when (this) {
                NORTH -> EAST
                EAST -> SOUTH
                SOUTH -> WEST
                WEST -> NORTH
            }
        }
    }
}
