package days


@AdventOfCodePuzzle(
    name = "Garden Groups",
    url = "https://adventofcode.com/2024/day/12",
    date = Date(day = 12, year = 2024)
)
class Day12(val input: List<String>) : Puzzle {

    private val garden: Map<Point, Char> = input.flatMapIndexed { y, line -> line.mapIndexed { x, c -> Point(x, y) to c } }.toMap()
    private val seen = mutableSetOf<Point>()
    private val regions: List<Region> = garden.keys.mapNotNull { findRegion(it, seen) }

    override fun partOne() = regions.sumOf { it.area * it.perimeter }

    override fun partTwo() = regions.sumOf { it.area * it.sides }

    private fun findRegion(start: Point, seen: MutableSet<Point>): Region? {
        val plant = garden[start]
        val queue = mutableListOf(start)
        val region = mutableSetOf<Point>()
        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            if (current in seen) continue
            region.add(current)
            seen.add(current)
            val neighbors = current.neighbors()
                .filter { garden[it] == plant && it !in seen }
            queue.addAll(neighbors)
        }
        return if (region.isNotEmpty()) Region(plant!!, region) else null
    }

    data class Region(val char: Char, val region: Set<Point>) {
        val area = region.size
        val perimeter: Int
            get() = region.sumOf { plant -> plant.neighbors().count { it !in region } }
        val corners: Int
            get() = region.sumOf { plant ->
                plant.corners().count {
                    (it[0] !in region && it[2] !in region)
                            || (it[0] in region && it[1] !in region && it[2] in region)
                }
            }
        val sides = corners
    }

    data class Point(val x: Int, val y: Int) {
        operator fun plus(other: Point) = Point(x + other.x, y + other.y)
        operator fun minus(other: Point) = Point(x - other.x, y - other.y)
        operator fun times(o: Int) = Point(x * o, y * o)
        operator fun Int.times(p: Point) = Point(p.x * this, p.y * this)
        operator fun List<Point>.contains(p: Point) = any { it == p }
        fun neighbors(): List<Point> = NEIGHBORS.map { this + it }
        fun corners() = CORNERS.map { (a, b) -> listOf(this + a, this + a + b, this + b) }

        companion object {
            private val NORTH = Point(-1, 0)
            private val WEST = Point(0, -1)
            private val SOUTH = Point(1, 0)
            private val EAST = Point(0, 1)
            val NEIGHBORS = listOf(EAST, SOUTH, WEST, NORTH)
            val CORNERS = listOf(EAST, SOUTH, WEST, NORTH, EAST).zipWithNext()
        }
    }

}