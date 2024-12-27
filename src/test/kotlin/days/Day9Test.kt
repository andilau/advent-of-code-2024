package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 9")
class Day9Test {
    val map = """2333133121414131402"""

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `What is the resulting filesystem checksum`() {
            assertThat(Day9(map).partOne()).isEqualTo(1928L)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `What is the resulting filesystem checksum`() {
            assertThat(Day9(map).partTwo()).isEqualTo(2858L)
        }

    }
}