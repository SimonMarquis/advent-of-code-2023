import Day03.Object.Part
import Day03.Object.Symbol
import kotlin.math.max
import kotlin.math.min

class Day03(private val input: List<String>) {

    sealed class Object {
        data class Part(val x: IntRange, val y: Int, val value: Int) : Object()
        data class Symbol(val x: Int, val y: Int, val value: Char) : Object() {
            val aoe = x.dec()..x.inc()
        }
    }

    private fun List<String>.schematic(regex: Regex = """(\d+|[^.])""".toRegex()): List<List<Object>> = mapIndexed { y, it ->
        regex.findAll(it).map {
            if (it.value.all(Char::isDigit)) Part(x = it.range, y = y, value = it.value.toInt())
            else Symbol(x = it.range.first, y = y, value = it.value.single())
        }.toList()
    }

    private fun <T> Iterable<T>.padded(padding: Iterable<T>) = padding + this + padding
    private fun <T> Iterable<T>.symbols(value: Char? = null) = filterIsInstance<Symbol>().filter { value == null || value == it.value }

    private fun MutableList<Object>.removePartsInRangeOf(symbol: Symbol): List<Part> = findPartsInRangeOf(symbol).also(::removeAll)
    private fun Iterable<Object>.findPartsInRangeOf(symbol: Symbol) = filterIsInstance<Part>().filter { symbol.aoe overlaps it.x }
    private infix fun IntRange.overlaps(other: IntRange) = max(first, other.first) <= min(last, other.last)

    fun part1() = input.schematic().map { it.toMutableList() }
        .padded(mutableListOf())
        .windowed(3) { (above, middle, below) ->
            listOf(above, middle, below).flatMap { line ->
                middle.symbols().flatMap { symbol -> line.removePartsInRangeOf(symbol) }
            }
        }.flatten()
        .sumOf(Part::value)

    fun part2() = input.schematic()
        .padded(emptyList())
        .windowed(3) { (above, middle, below) ->
            middle.symbols('*').mapNotNull { symbol ->
                listOf(above, middle, below).flatMap { it.findPartsInRangeOf(symbol) }
                    .takeIf { it.size == 2 }
                    ?.let { (a, b) -> a to b }
            }
        }.flatten()
        .sumOf { it.first.value * it.second.value }

}
