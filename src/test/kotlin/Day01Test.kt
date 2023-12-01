import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 01")
class Day01Test {

    private val sampleInput = readLines("Day01-sample.txt")
    private val sampleInput2 = readLines("Day01-sample2.txt")
    private val actualInput = readLines("Day01.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 142,
            actual = Day01(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 55029,
            actual = Day01(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 281,
            actual = Day01(sampleInput2).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 55686,
            actual = Day01(actualInput).part2(),
        )

    }

}
