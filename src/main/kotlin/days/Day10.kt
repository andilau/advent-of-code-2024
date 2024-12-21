package days

import java.util.PriorityQueue

@AdventOfCodePuzzle(
    name = "Hoof It",
    url = "https://adventofcode.com/2024/day/10",
    date = Date(day = 10, year = 2024)
)
class Day10(val input: List<String>) : Puzzle {

    val map: Map<Point, Int> = findAllIgnoring().toMap()
    val starts: Set<Point> = map.filter { (_, v) -> v == 0 }.keys
    val exits: Set<Point> = map.filter { (_, v) -> v == 9 }.keys

    override fun partOne() = starts.sumOf { start -> scoreTrail(start, exits) }

    override fun partTwo() = starts.sumOf { start -> scoreTrail2(start, exits) }

    private fun findAllIgnoring(): List<Pair<Point, Int>> = input
        .flatMapIndexed { y, line -> line.mapIndexedNotNull() { x, c -> if (c.isDigit()) Point(x, y) to c.digitToInt() else null } }

    private fun scoreTrail(start: Point, exits: Set<Point>): Int {
        var reached = mutableSetOf<Point>()
        val queue = PriorityQueue<Pair<Point, Int>> { a, b -> a.second.compareTo(b.second) }
        queue.add(start to 0)
        val seen = mutableSetOf<Point>()

        while (queue.isNotEmpty()) {
            val (next, cost) = queue.poll()

            if (next in seen) continue
            seen += next
            if (next in exits) reached += next
            next.neighbors()
                .filter { it in map }
                .filter { map[it]!! - map[next]!! == 1 }
                .forEach { queue.add(it to cost + 1) }
        }
        return reached.size
    }

    private fun scoreTrail2(start: Point, exits: Set<Point>): Int {
        var reached = mutableListOf<Point>()
        val queue = PriorityQueue<List<Point>> { a, b -> a.size.compareTo(b.size) }
        queue.add(listOf(start))
        while (queue.isNotEmpty()) {
            val path = queue.poll()

            if (path.last() in exits) reached += path.last()
            path.last().neighbors()
                .filter { it in map }
                .filter { map[it]!! - map[path.last()]!! == 1 }
                .forEach { queue.add(path + it) }
        }
        return reached.size
    }
}