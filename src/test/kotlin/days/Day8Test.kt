package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 8")
class Day8Test {
    val map = """
        ............
        ........0...
        .....0......
        .......0....
        ....0.......
        ......A.....
        ............
        ............
        ........A...
        .........A..
        ............
        ............""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `How many unique locations within the bounds of the map contain an antinode?`() {
            assertThat(Day8(map).partOne()).isEqualTo(14)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `How many unique locations within the bounds of the map contain an antinode?`() {
            assertThat(Day8(map).partTwo()).isEqualTo(34)
        }

    }
}