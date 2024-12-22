package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

@DisplayName("Day 22")
class Day22Test {
    val input = """
        1
        10
        100
        2024""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `what is the minimum number of steps needed to reach the exit`() {
            //assertThat(Day22(input).next(input.map { it.toInt() })).isEqualTo(listOf(0, 0, 0, 0))
            assertThat(Day22(listOf("123")).partOne()).isEqualTo(22)
        }

        @Test
        fun `2`() {
            //assertThat(Day22(input).next(input.map { it.toInt() })).isEqualTo(listOf(0, 0, 0, 0))
            assertThat(Day22(input).partOne()).isEqualTo(37327623)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `what is the minimum number of steps needed to reach the exit`() {
            assertThat(Day22(listOf("123")).partTwo()).isEqualTo(6)
        }

        @Test
        fun `what is the minimum number of steps needed to reach the exit 2`() {
            val input = """
                1
                2
                3
                2024""".trimIndent().lines()
            assertThat(Day22(input).partTwo()).isEqualTo(23)
        }
    }

}