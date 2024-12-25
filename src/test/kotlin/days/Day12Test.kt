package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestFactory

@DisplayName("Day 12")
class Day12Test {

    val map1 = """
        AAAA
        BBCD
        BBCC
        EEEC""".trimIndent().lines()

    val map2 = """
        OOOOO
        OXOXO
        OOOOO
        OXOXO
        OOOOO""".trimIndent().lines()

    val map3 = """
        RRRRIICCFF
        RRRRIICCCF
        VVRRRCCFFF
        VVRCCCJFFF
        VVVVCJJCFE
        VVIVCCJJEE
        VVIIICJJEE
        MIIIIIJJEE
        MIIISIJEEE
        MMMISSJEEE""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @TestFactory
        fun `What is the total price of fencing all regions on your map`() =
            listOf(map1 to 140, map2 to 772, map3 to 1930).mapIndexed { i, (input, expected) ->
                DynamicTest.dynamicTest("Given the map ${i}, the total price of fencing all regions should be $expected") {
                    assertThat(Day12(input).partOne()).isEqualTo(expected)
                }
            }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        val map4 = """
            EEEEE
            EXXXX
            EEEEE
            EXXXX
            EEEEE
        """.trimIndent().lines()

        val map5 = """
            AAAAAA
            AAABBA
            AAABBA
            ABBAAA
            ABBAAA
            AAAAAA""".trimIndent().lines()

        @TestFactory
        fun `What is the total price of fencing all regions on your map`() =
            listOf(map1 to 80, map2 to 436, map3 to 1206, map4 to 236, map5 to 368)
                .mapIndexed { i, (input, expected) ->
                    DynamicTest.dynamicTest("Given the map ${i}, the total price of fencing all regions should be $expected") {
                        assertThat(Day12(input).partTwo()).isEqualTo(expected)
                    }
                }

    }
}