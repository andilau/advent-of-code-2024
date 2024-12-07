package days

@AdventOfCodePuzzle(
    name = "Print Queue",
    url = "https://adventofcode.com/2024/day/5",
    date = Date(day = 5, year = 2024)
)
class Day5(input: List<String>) : Puzzle {

    private val pageOrderingRules = input
        .takeWhile { it.isNotEmpty() }
        .map { line -> line.split('|').let { it[0].toInt() to it[1].toInt() } }

    private val pageToProduce = input
        .dropWhile { it.isNotEmpty() }
        .drop(1)
        .map { it.split(',').map { it.toInt() } }

    override fun partOne(): Int = pageToProduce
        .filter { pages -> validOrder(pages) }
        .sumOf(::middlePage)

    override fun partTwo() = pageToProduce
        .filterNot { pages -> validOrder(pages) }
        .map { pages -> reorder(pages) }
        .sumOf { middlePage(it) }

    private fun validOrder(pages: List<Int>) = pageOrderingRules
        .filter { (prev, next) -> prev in pages && next in pages }
        .all { (prev, next) -> pages.indexOf(prev) < pages.indexOf(next) }

    private fun reorder(pages: List<Int>): List<Int> {
        val selectedRules = pageOrderingRules
            .filter { (prev, next) -> prev in pages && next in pages }
        val outEdges = selectedRules
            .groupingBy { it.first }.eachCount().toMutableMap()
        selectedRules.map { it.second }.forEach { outEdges.putIfAbsent(it, 0) }

        return outEdges
            .map { (page, degree) -> degree to page }
            .toMap().toSortedMap(Comparator.naturalOrder<Int?>().reversed())
            .values.toList()
    }

    private fun middlePage(it: List<Int>) = it[it.size / 2]

}