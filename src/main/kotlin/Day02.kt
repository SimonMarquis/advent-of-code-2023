import Day02.Color.blue
import Day02.Color.green
import Day02.Color.red

class Day02(private val input: List<String>) {

    @Suppress("EnumEntryName")
    enum class Color { red, green, blue }

    private val regex = """(\d+) (${Color.entries.joinToString("|")})""".toRegex()

    fun part1(
        target: Map<Color, Int> = mapOf(red to 12, green to 13, blue to 14),
    ): Int = input.withIndex().filter { (_, line) ->
        line.split(";")
            .map { it.cubes() }
            .all { it.validate(target) }
    }.sumOf { it.index + 1 }

    fun part2() = input.withIndex().sumOf { (_, line) ->
        val cubes = line.split(";").map { it.cubes() }
        fun maxOf(color: Color) = cubes.maxOf { it.getValue(color) }
        maxOf(red) * maxOf(green) * maxOf(blue)
    }

    private fun String.cubes(): Map<Color, Int> = regex.findAll(this)
        .map { it.destructured.let { (amount, color) -> Color.valueOf(color) to amount.toInt() } }
        .foldInPlace(mutableMapOf<Color, Int>().withDefault { 0 }) { (color, amount) -> merge(color, amount, Int::plus) }

    private fun Map<Color, Int>.validate(target: Map<Color, Int>) = target.all { (key, value) -> getValue(key) <= value }

}
