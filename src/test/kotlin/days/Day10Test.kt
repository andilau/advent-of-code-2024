package days

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

@DisplayName("Day 10")
class Day10Test {
    val trailheadScore1 = """
        0123
        1234
        8765
        9876""".trimIndent().lines()

    val trailheadScore2 = """
        ...0...
        ...1...
        ...2...
        6543456
        7.....7
        8.....8
        9.....9""".trimIndent().lines()
    val trailheadScore3 = """
        ..90..9
        ...1.98
        ...2..7
        6543456
        765.987
        876....
        987....""".trimIndent().lines()
    val trailheadScore4 = """
        10..9..
        2...8..
        3...7..
        4567654
        ...8..3
        ...9..2
        .....01""".trimIndent().lines()
    val trailheadScore5 = """
        89010123
        78121874
        87430965
        96549874
        45678903
        32019012
        01329801
        10456732""".trimIndent().lines()

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @TestFactory
        fun `What is the sum of the scores of all trailheads on your topographic map`() =
            listOf(
                trailheadScore1 to 1,
                trailheadScore2 to 2,
                trailheadScore3 to 4,
                trailheadScore4 to 3,
                trailheadScore5 to 36,
            ).map { (map, score) ->
                DynamicTest.dynamicTest("The sum of the scores of all trailheads on the topographic map is $score") {
                    assertThat(Day10(map).partOne()).isEqualTo(score)
                }
            }

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        val trailheadScore6 = """
            .....0.
            ..4321.
            ..5..2.
            ..6543.
            ..7..4.
            ..8765.
            ..9....""".trimIndent().lines()

        val trailheadScore7 = """
            ..90..9
            ...1.98
            ...2..7
            6543456
            765.987
            876....
            987....""".trimIndent().lines()

        val trailheadScore8 = """
            012345
            123456
            234567
            345678
            4.6789
            56789.""".trimIndent().lines()
        val trailheadScore9 = """
            89010123
            78121874
            87430965
            96549874
            45678903
            32019012
            01329801
            10456732""".trimIndent().lines()

        @TestFactory
        fun `What is the sum of the scores of all trailheads on your topographic map`() =
            listOf(
                trailheadScore6 to 3,
                trailheadScore7 to 13,
                trailheadScore8 to 227,
                trailheadScore9 to 81,
            ).map { (map, score) ->
                DynamicTest.dynamicTest("The sum of the scores of all trailheads on the topographic map is $score") {
                    assertThat(Day10(map).partTwo()).isEqualTo(score)
                }
            }
    }
}