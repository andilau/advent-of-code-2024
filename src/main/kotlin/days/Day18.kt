package days

import java.util.*

@AdventOfCodePuzzle(
    name = "RAM Run",
    url = "https://adventofcode.com/2024/day/18",
    date = Date(day = 18, year = 2024)
)
class Day18(val input: List<String>) : Puzzle {

    private val elements = if (input.size < 100) 12 else 1024
    private val max = if (input.size < 100) 6 else 70

    private val bytes: List<Point> = input.take(elements).map { line -> Point.from(line) }
    private val range = 0..max
    private val start = Point(range.start, range.start)
    private val exit = Point(range.last, range.last)

    override fun partOne() = distance(bytes)

    override fun partTwo(): String = (elements..input.lastIndex).firstNotNullOf {
        val b = input.take(it).map { line -> Point.from(line) }
        try {
            distance(b)
            null
        } catch (e: Exception) {
            b.last().let { (x, y) -> "${x},${y}" }
        }
    }

    private fun distance(bytes: List<Point>): Int {
        val queue = PriorityQueue<Pair<Point, Int>> { a, b -> a.second.compareTo(b.second) }
        queue.add(start to 0)
        val seen = mutableSetOf<Point>()

        while (queue.isNotEmpty()) {
            val (next, cost) = queue.poll()

            if (next in seen) continue
            seen += next
            if (next == exit) return cost
            next.neighbors()
                .filter { it.x in range && it.y in range }
                .filter { it !in bytes }
                .forEach { queue.add(it to cost + 1) }
        }
        error("No path found!")
    }

}