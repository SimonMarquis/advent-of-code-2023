class Day06(private val input: List<String>) {

    fun part1(): Long = input
        .map { """\d+""".toRegex().findAll(it).map(MatchResult::value).map(String::toLong) }
        .let { (times, distances) -> (times zip distances) }
        .map { (time, distance) -> wins(time, distance) }
        .product()

    fun part2(): Long = input
        .map { it.filter(Char::isDigit).toLong() }
        .let { (time, distance) -> wins(time, distance) }

    private fun wins(time: Long, distance: Long): Long {
        var min = 0
        while (min * (time - min) <= distance) min++
        var max = time
        while (max * (time - max) <= distance) max--
        return (max - min + 1)
    }

}
