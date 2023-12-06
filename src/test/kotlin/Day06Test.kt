import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day 06")
class Day06Test {

    private val sampleInput = readLines("Day06-sample.txt")
    private val actualInput = readLines("Day06.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 288,
            actual = Day06(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 449820,
            actual = Day06(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 71503,
            actual = Day06(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 42250895,
            actual = Day06(actualInput).part2(),
        )

    }

}
