package days


@AdventOfCodePuzzle(
    name = "Disk Fragmenter",
    url = "https://adventofcode.com/2024/day/9",
    date = Date(day = 9, year = 2024)
)
class Day9(val input: String) : Puzzle {

    override fun partOne(): Long = input.unpack().compress().checksum()

    override fun partTwo(): Long = input.unpack().compressFiles().checksum()

    private fun String.unpack(): List<Int> = chunked(2).withIndex()
        .flatMap {
            List(it.value[0].digitToInt(), { _ -> it.index }) +
                    if (it.value.length == 2) List(it.value[1].digitToInt(), { _ -> -1 }) else emptyList()
        }

    private fun List<Int>.compress(): List<Int> {
        val unpack = toMutableList()
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

    private fun List<Int>.compressFiles(): List<Int> {
        val unpack = toMutableList()
        var y1 = unpack.lastIndex
        var y2: Int

        (unpack.last() downTo 0).forEach { id ->
            println("id = ${id}")
            run {

                while (unpack[y1] != id) y1--
                y2 = y1
                while (y2 in unpack.indices && unpack[y1] == unpack[y2]) y2--

                val size = y1 - y2
                var x1 = 0
                var x2: Int
                while (true) {
                    while (unpack[x1] != -1) x1++
                    x2 = x1
                    while (x2 !in unpack.indices || unpack[x1] == unpack[x2]) x2++
                    if (x2 - x1 >= size && x1 < y2) {
                        (x1..<x1 + size).forEach { unpack[it] = unpack[y1] }
                        ((y2 + 1)..y1).forEach { unpack[it] = -1 }
                        break
                    }
                    if (x1 !in unpack.indices || x2 !in unpack.indices || x1 > y1)
                        break
                    x1++
                }

                y1 = y2
            }
        }
        return unpack
    }

    private fun List<Int>.checksum(): Long =
        mapIndexed { index, i -> if (i >= 0) index * i.toLong() else 0 }.sum()
}

private fun List<Int>.show(): List<Int> {
    this.joinToString("|") { if (it <= 0) "." else "${it}" }.also { println(it) }
    return this
}
