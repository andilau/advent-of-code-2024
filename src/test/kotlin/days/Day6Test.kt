package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 6")
class Day6Test {
    val memory = """
        ....#.....
        .........#
        ..........
        ..#.......
        .......#..
        ..........
        .#..^.....
        ........#.
        #.........
        ......#...""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `How many distinct positions will the guard visit before leaving the mapped area`() {
            assertThat(Day6(memory).partOne()).isEqualTo(41)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `How many different positions could you choose for this obstruction`() {
            assertThat(Day6(memory).partTwo()).isEqualTo(6)
        }

    }
}