import days.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import util.InputReader

@DisplayName("Solutions")
class SolutionsTest {
    @TestFactory
    fun testAdventOfCode() = listOf(
        Day1(InputReader.getInputAsList(1)) to Pair(1879048, 21024792),
        Day2(InputReader.getInputAsList(2)) to Pair(559, 601),
        Day3(InputReader.getInputAsList(3)) to Pair(178886550, 87163705),
        Day4(InputReader.getInputAsList(4)) to Pair(2414, 1871),
        Day5(InputReader.getInputAsList(5)) to Pair(6041, 4884),
        Day7(InputReader.getInputAsList(7)) to Pair(267566105056L, 116094961956019L),
        Day8(InputReader.getInputAsList(8)) to Pair(371, 1229),
        Day10(InputReader.getInputAsList(10)) to Pair(501, 1017),
        Day12(InputReader.getInputAsList(12)) to Pair(1464678, 877492),
        Day14(InputReader.getInputAsList(14)) to Pair(214109808L, 7687),
        Day18(InputReader.getInputAsList(18)) to Pair(370, "65,6"),
        Day22(InputReader.getInputAsList(22)) to Pair(14869099597L, 1717),
        Day23(InputReader.getInputAsList(23)) to Pair(0, 0),
    )
        .map { (day, answers) ->
            DynamicTest.dynamicTest("${day.javaClass.simpleName} -> ${answers.first} / ${answers.second}") {
                assertThat(day.partOne()).isEqualTo(answers.first)
                assertThat(day.partTwo()).isEqualTo(answers.second)
            }
        }
}