import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

@DisplayName("Day 10")
class Day10Test {

    private val sampleInput1 = readLines("Day10-sample-1.txt")
    private val sampleInput2 = readLines("Day10-sample-2.txt")
    private val sampleInput3 = readLines("Day10-sample-3.txt")
    private val actualInput = readLines("Day10.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() = assertEquals(
            expected = 4,
            actual = Day10(sampleInput1).part1(),
        )

        @Test
        fun `Matches example 2`() = assertEquals(
            expected = 8,
            actual = Day10(sampleInput2).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 6786,
            actual = Day10(actualInput).part1(),
        )

    }

    @Ignore
    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 10,
            actual = Day10(sampleInput3).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 0,
            actual = Day10(actualInput).part2(),
        )

    }

}
