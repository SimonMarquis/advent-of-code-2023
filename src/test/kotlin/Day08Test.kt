import Resources.readLines
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.Ignore
import kotlin.test.assertEquals

@DisplayName("Day 08")
class Day08Test {

    private val sampleInput1 = readLines("Day08-sample-1.txt")
    private val sampleInput2 = readLines("Day08-sample-2.txt")
    private val sampleInput3 = readLines("Day08-sample-3.txt")
    private val actualInput = readLines("Day08.txt")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {

        @Test
        fun `Matches example 1`() = assertEquals(
            expected = 2,
            actual = Day08(sampleInput1).part1(),
        )

        @Test
        fun `Matches example 2`() = assertEquals(
            expected = 6,
            actual = Day08(sampleInput2).part1(),
        )

        @Test
        fun `Actual answer`() = assertEquals(
            expected = 16531,
            actual = Day08(actualInput).part1(),
        )

    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {

        @Test
        fun `Matches example`() = assertEquals(
            expected = 6,
            actual = Day08(sampleInput3).part2(),
        )


        @Test
        fun `Actual answer`() = assertEquals(
            expected = 24035773251517,
            actual = Day08(actualInput).part2(),
        )

    }

}
