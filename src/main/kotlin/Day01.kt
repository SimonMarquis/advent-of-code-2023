class Day01(private val input: List<String>) {

    fun part1() = input.asSequence()
        .map { it.filter(Char::isDigit) }
        .sumOf { "${it.first()}${it.last()}".toInt() }

    private val replacements = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")

    fun part2() = input.asSequence()
        .map { line ->
            line.foldIndexed(StringBuilder()) { accIndex, acc, c ->
                if (c.isDigit()) return@foldIndexed acc.append(c)
                replacements.forEachIndexed { index, replacement ->
                    if (line.startsWith(replacement, startIndex = accIndex)) return@foldIndexed acc.append(index + 1)
                }
                return@foldIndexed acc
            }
        }
        .sumOf { "${it.first()}${it.last()}".toInt() }

}

