package days

@AdventOfCodePuzzle(
    name = "Ceres Search",
    url = "https://adventofcode.com/2024/day/4",
    date = Date(day = 4, year = 2024)
)
class Day4(val memory: List<String>) : Puzzle {

    private val xmas = "XMAS".toRegex()
    private val samx = "SAMX".toRegex()

    val valid = mapOf(
        Point(0, 0) to 'X',
    )

    override fun partOne(): Int = memory.sumOf { xmas.findAll(it).count() + samx.findAll(it).count() } +
            memory.mapIndexed { index, s -> " ".repeat(index) + s }.sumOf { line -> xmas.findAll(line).count() }

    override fun partTwo(): Int = 0


    data class Point(val x: Int, val y: Int)
}