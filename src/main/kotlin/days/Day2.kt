package days

@AdventOfCodePuzzle(
    name = "Red-Nosed Reports",
    url = "https://adventofcode.com/2024/day/2",
    date = Date(day = 2, year = 2024)
)
class Day2(lines: List<String>) : Puzzle {

    private val reports: List<Report> = lines.map { line -> Report.from(line) }

    override fun partOne(): Int = reports.count(Report::safe)

    override fun partTwo(): Int = reports.count { it.safeWithTolerance() }

    data class Report(val level: List<Int>) {

        fun safe() = isSafe(diff(level))

        fun safeWithTolerance() = level.indices.any { ix ->
            isSafe(diff(level.toMutableList().apply { removeAt(ix) }))
        }

        private fun diff(list: List<Int>) = list.zipWithNext().map { it.first - it.second }

        private fun isSafe(list: List<Int>) = increasing(list) || decreasing(list)

        private fun increasing(diffs: List<Int>) = diffs.all { it in 1..3 }

        private fun decreasing(diffs: List<Int>) = diffs.all { it in -3..-1 }

        companion object {
            fun from(line: String): Report {
                val regex = """\s+""".toRegex()
                return Report(line.split(regex).map {
                    it.toInt()
                })
            }
        }
    }
}