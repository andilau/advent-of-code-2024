package days

@AdventOfCodePuzzle(
    name = "Mull It Over",
    url = "https://adventofcode.com/2024/day/3",
    date = Date(day = 3, year = 2024)
)
class Day3(val memory: List<String>) : Puzzle {

    val regex = """mul\((\d{1,3}+),(\d{1,3}+)\)""".toRegex()
    val regex2 = """mul\((\d{1,3}+),(\d{1,3}+)\)|(do\(\))|(don't\(\))""".toRegex()

    override fun partOne(): Int = regex
        .findAll(memory.joinToString())
        .map { it.groupValues.drop(1).map { it.toInt() }.fold(1) { a, b -> a * b } }
        .sum()

    override fun partTwo(): Int = regex2
        .findAll(memory.joinToString())
        .map {
            val what = it.groupValues.first()
            when (what) {
                "do()" -> 1 to 0
                "don't()" -> 0 to 0
                else -> 1 to it.groupValues.drop(1).take(2).map { it.toInt() }.fold(1) { a, b -> a * b }
            }
        }
        .fold(1 to 0) { acc, p ->
            Pair(
                if (p.second == 0) p.first else acc.first,
                acc.second + acc.first * p.second
            )
        }
        .second

}