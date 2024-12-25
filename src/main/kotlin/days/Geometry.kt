package days

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

        fun from(line: String) = line.split(',').map { it.toInt() }.let { (x, y) -> Point(x, y) }
    }
}