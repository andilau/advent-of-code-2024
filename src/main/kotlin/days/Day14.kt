package days

@AdventOfCodePuzzle(
    name = "Restroom Redoubt",
    url = "https://adventofcode.com/2024/day/14",
    date = Date(day = 14, year = 2024)
)
class Day14(val input: List<String>) : Puzzle {

    val robots: List<Robot> = input.map { line -> Robot.from(line) }

    val bounds = if (input.size < 100) Point(11, 7) else Point(101, 103)

    override fun partOne() = robots
        .map { it.move(100).bounds(bounds) }
        .groupingBy() { quadrant(it.position) }.eachCount()
        .filter { it.key != 0 }
        .values
        .fold(1L) { a, b -> a * b }

    override fun partTwo(): Int =
        generateSequence(0) { it + 1 }
            .firstNotNullOfOrNull { seconds ->
                val count = robots
                    .map { it.move(seconds).bounds(bounds).position }
                    .toSet().count()
                if (count == input.size) seconds else null
            } ?: error("No solution found")

    fun draw(robots: List<Robot>) {
        (0..bounds.y).forEach { y ->
            (0..bounds.x).map { x ->
                if (robots.any { it.position == Point(x, y) }) "#" else "."
            }.joinToString("").also { println(it) }
        }
    }

    private fun quadrant(p: Point): Int =
        when {  // top left, top right, bottom left, bottom right
            p.x in 0..<(bounds.x / 2) && p.y in 0..<(bounds.y / 2) -> 1      // top left
            p.x in (bounds.x / 2 + 1)..bounds.x && p.y in 0..<(bounds.y / 2) -> 2    // top right
            p.x in 0..<(bounds.x / 2) && p.y in (bounds.y / 2 + 1)..bounds.y -> 3     // bottom left
            p.x in (bounds.x / 2 + 1)..bounds.x && p.y in (bounds.y / 2 + 1)..bounds.y -> 4     // bottom right
            else -> 0
        }

    data class Robot(val position: Point, val velocity: Point) {
        fun move(times: Int) = Robot(position + (velocity * times), velocity)
        fun bounds(bounds: Point) = Robot(position.moveToBounds(bounds), velocity)

        companion object {
            fun from(line: String): Robot {
                val (p, v) = line.split(" ")
                val position = p.removePrefix("p=").split(",").map { it.toInt() }
                val velocity = v.removePrefix("v=").split(",").map { it.toInt() }
                return Robot(Point(position[0], position[1]), Point(velocity[0], velocity[1]))
            }
        }
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point) = Point(x + other.x, y + other.y)
        operator fun minus(other: Point) = Point(x - other.x, y - other.y)
        operator fun times(o: Int) = Point(x * o, y * o)

        fun moveToBounds(bounds: Point) = Point(x.mod(bounds.x), y.mod(bounds.y))
    }

}