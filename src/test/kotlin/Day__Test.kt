import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

@DisplayName("Day __")
class Day__Test {

    private val sampleInput = readLines("Day__-sample.txt")
    private val actualInput = readLines("Day__.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = Unit,
            actual = Day__(sampleInput).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = Unit,
            actual = Day__(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = Unit,
            actual = Day__(sampleInput).part2(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = Unit,
            actual = Day__(actualInput).part2(),
        )

    }

}