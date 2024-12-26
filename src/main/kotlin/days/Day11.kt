package days

@AdventOfCodePuzzle(
    name = "Plutonian Pebbles",
    url = "https://adventofcode.com/2024/day/11",
    date = Date(day = 11, year = 2024)
)
class Day11(val input: String) : Puzzle {
    override fun partOne(): Int {
        println("input = ${input}")
        val times = 75
        val joined = (1..times).fold(input.split(" ").map { it.toLong() }) { stones, _ -> stones.blink().also { println(it) } }
        println("joined = ${joined}")
        return joined.size
    }

    fun List<Long>.blink(times: Int = 1): List<Long> {
        return flatMap {
            when {
                it == 0L -> listOf(1L)
                "$it".length % 2 == 0 -> listOf(
                    "$it".slice(0..<"$it".length / 2).toLong(),
                    "$it".slice("$it".length / 2..<"$it".length).toLong()
                )
                else -> listOf(it * 2024)
            }
        }
    }

    override fun partTwo() = 0
}