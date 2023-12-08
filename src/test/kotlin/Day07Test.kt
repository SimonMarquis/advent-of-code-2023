import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 07")
class Day07Test {

    private val sampleInput = readLines("Day07-sample.txt")
    private val actualInput = readLines("Day07.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 6440,
            actual = Day07(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 253954294,
            actual = Day07(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 5905,
            actual = Day07(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 254837398,
            actual = Day07(actualInput).part2(),
        )

    }

}
