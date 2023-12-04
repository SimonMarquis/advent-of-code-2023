import kotlin.math.pow

class Day04(private val input: List<String>) {

    data class Card(val win: Set<Int>, val hand: Set<Int>)

    private val cards = input.asSequence().map { card ->
        card.substringAfter(":")
            .split("|")
            .map { it.split(" ").mapNotNull(String::toIntOrNull).toSet() }
            .let { Card(win = it.first(), hand = it.last()) }
    }

    fun part1() = cards
        .onEach(::println)
        .map { it.win intersect it.hand }
        .onEach { println("Winning numbers: $it") }
        .filter { it.isNotEmpty() }
        .map { 2.0.pow(it.size - 1).toInt() }
        .onEach { println("Score: $it") }
        .sum()

    fun part2() = cards
        .onEach(::println)
        .map { (it.win intersect it.hand).size }
        .foldIndexedInPlace(MutableList(size = input.size) { 1 }) { index: Int, copies: Int ->
            repeat(copies) { offset -> this[index.inc() + offset] += this[index] }
            println("Cards: ${this[index]}, Copies: $copies, Distributed: $this")
        }.sum()

}
