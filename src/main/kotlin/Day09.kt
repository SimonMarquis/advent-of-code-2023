class Day09(input: List<String>) {

    private val histories = input.asSequence()
        .map { it.split(" ").map(String::toLong) }
        .map { it.differences() }

    private fun List<Long>.differences(): List<List<Long>> = buildList {
        add(this@differences)
        while (last().any { it != 0L }) add(last().windowed(size = 2) { (l, r) -> r - l })
    }

    fun part1(): Long = histories
        .sumOf { it.reversed().fold(0L) { acc, values -> acc + values.last() } }

    fun part2(): Long = histories
        .sumOf { it.reversed().fold(0L) { acc, values -> values.first() - acc } }

}
