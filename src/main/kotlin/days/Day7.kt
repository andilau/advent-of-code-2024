package days

@AdventOfCodePuzzle(
    name = "Bridge Repair",
    url = "https://adventofcode.com/2024/day/7",
    date = Date(day = 7, year = 2024)
)
class Day7(input: List<String>) : Puzzle {

    private val equations: List<Pair<Long, List<Long>>> =
        input.map { it.substringBefore(": ").toLong() to it.substringAfter(": ").split(" ").map { it.toLong() } }

    private val add: (Long, Long) -> Long = { a, b -> a + b }
    private val mul: (Long, Long) -> Long = { a, b -> a * b }
    private val con: (Long, Long) -> Long = { a, b -> "$a$b".toLong() }

    override fun partOne() = equations
        .filter { computeEquals(it.first, it.second, listOf(add, mul)) }
        .sumOf { it.first }

    override fun partTwo() = equations
        .filter { computeEquals(it.first, it.second, listOf(add, mul, con)) }
        .sumOf { it.first }

    private fun computeEquals(excepted: Long, args: List<Long>, ops: List<(Long, Long) -> Long>): Boolean {
        if (args.size == 1 || excepted < args[0]) {
            return excepted == args[0]
        }

        return ops.any { op -> computeEquals(excepted, listOf(op(args[0], args[1])) + args.drop(2), ops) }
    }

}