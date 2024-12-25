package days

@AdventOfCodePuzzle(
    name = "LAN Party",
    url = "https://adventofcode.com/2024/day/23",
    date = Date(day = 23, year = 2024)
)
class Day23(val input: List<String>) : Puzzle {

    override fun partOne(): Int {
        return links.keys
            .filter { it.startsWith('t') }
            .flatMap { s ->
                pairs(links.getValue(s).toList()).filter { (b, c) -> c in links.getValue(b) }
                    .map { (it.toList() + s).toSet() }
            }
            .distinct()
            .also { println(it) }
            .size
    }

    override fun partTwo() = ""

    private val links: Map<String, Set<String>> =
        input.map { it.split('-') }.flatMap { (l, r) -> listOf(l to r, r to l) }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.toSet() }

    private fun <T> pairs(list: List<T>): List<Pair<T, T>> {
        return list.flatMapIndexed { ix, e -> list.drop(ix + 1).map { e to it } }
    }

}

