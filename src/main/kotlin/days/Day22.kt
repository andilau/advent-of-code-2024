package days

@AdventOfCodePuzzle(
    name = "Monkey Market",
    url = "https://adventofcode.com/2024/day/22",
    date = Date(day = 22, year = 2024)
)
class Day22(val input: List<String>) : Puzzle {
    val secrets = input.map { it.toInt() }

    override fun partOne(): Long {
        return secrets.sumOf { secret ->
            generateSequence(secret) { it.next() }
                .take(2001)
                .last().toLong()
        }
    }

    override fun partTwo(): Int {
        val seqPriceSums = mutableMapOf<List<Int>, Int>()
        secrets.forEach { secret ->
            println("secret = ${secret}")
            val prices: List<Int> = generateSequence(secret) { it.next() }
                .take(2001)
                .map { it.mod(10) }
                .toList()
            val sequences = prices
                .zipWithNext { a, b -> b - a }
                .windowed(4)
                .toList()
            val zip = prices.drop(4).zip(sequences)
            val seen = mutableSetOf<List<Int>>()
            for ((price, seq) in zip) {
                if (seq in seen) continue
                seen += seq
                seqPriceSums.compute(seq) { _, v -> v?.plus(price) ?: price }
            }
        }
        return seqPriceSums.values.maxOrNull() ?: 0
    }

    fun next(nums: List<Int>): List<Int> {
        return nums.map { it.next() }
    }

    fun Int.next(): Int {
        var x = this
        x = x.xor(x * 64).mod(16777216)
        x = x.xor(x / 32).mod(16777216)
        x = x.xor(x * 2048).mod(16777216)
        return x
    }

}