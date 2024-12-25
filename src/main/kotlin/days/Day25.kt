package days

@AdventOfCodePuzzle(
    name = "Code Chronicle",
    url = "https://adventofcode.com/2024/day/25",
    date = Date(day = 25, year = 2024)
)
class Day25(val input: String) : Puzzle {
    private val securities: List<Security> = input.split("\n\n").map { Security.from(it) }

    override fun partOne(): Int = securities
        .filterIsInstance<Security.Lock>()
        .flatMap { key -> securities.filterIsInstance<Security.Key>().map { lock -> key to lock } }
        .count { (lock, key) -> lock.signature.zip(key.signatur).all { (l, p) -> l + p <= 5 } }

    override fun partTwo() = Unit

    sealed class Security() {
        data class Lock(val signature: List<Int>) : Security()
        data class Key(val signatur: List<Int>) : Security()

        companion object {
            fun from(input: String): Security {
                val lines = input.lines()
                return when {
                    lines.first().all { it == '#' } -> Lock(pivot(lines.drop(1)).map { it.count { it == '#' } })
                    lines.last().all { it == '#' } -> Key(pivot(lines.dropLast(1)).map { it.count { it == '#' } })
                    else -> error("Something else")
                }
            }

            private fun pivot(list: List<String>): List<String> {
                return list[0].indices.map { col -> list.map { it[col] }.joinToString("") }
            }
        }
    }
}