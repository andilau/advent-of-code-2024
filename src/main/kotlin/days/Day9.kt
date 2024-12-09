package days


@AdventOfCodePuzzle(
    name = "Disk Fragmenter",
    url = "https://adventofcode.com/2024/day/9",
    date = Date(day = 9, year = 2024)
)
class Day9(val input: String) : Puzzle {

    override fun partOne(): Long = input.unpack().compress().checksum()

    override fun partTwo(): Int {
        return 0
    }

    fun String.unpack(): List<Int> = chunked(2).withIndex()
        .flatMap {
            List(it.value[0].digitToInt(), { _ -> it.index }) +
                    if (it.value.length == 2) List(it.value[1].digitToInt(), { _ -> -1 }) else emptyList()
        }

    fun List<Int>.compress(): List<Int> {
        var unpack = toMutableList()
        var x = 0
        var y = unpack.lastIndex
        while (true) {
            while (unpack[x] != -1) {
                x++
            }
            while (unpack[y] == -1) {
                y--
            }
            if (x >= y) {
                break
            }
            unpack[x] = unpack[y]
            unpack[y] = -1
        }
        return unpack
    }

    fun List<Int>.checksum(): Long = takeWhile { it != -1 }.mapIndexed { index, i -> index * i.toLong() }.sum()
}