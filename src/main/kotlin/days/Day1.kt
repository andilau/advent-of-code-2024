package days

import kotlin.math.abs

@AdventOfCodePuzzle(
    name = "Historian Hysteria",
    url = "https://adventofcode.com/2024/day/1",
    date = Date(day = 1, year = 2024)
)
class Day1(input: List<String>) : Puzzle {

    private val lines: List<Pair<Int, Int>> = input
        .map { line -> line.split("""\s+""".toRegex()).let { (a, b) -> a.toInt() to b.toInt() } }
    private val left = lines.map { it.first }.sorted()
    private val right = lines.map { it.second }.sorted()
    private val frequencies = right.groupingBy { it }.eachCount()

    override fun partOne(): Int = left
        .zip(right)
        .sumOf { pair -> abs(pair.second - pair.first) }

    override fun partTwo(): Int = left
        .fold(0) { acc, num -> acc + num * frequencies.getOrDefault(num, 0) }

}