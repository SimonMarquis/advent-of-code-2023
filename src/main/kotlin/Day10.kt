import Day10.Pipe.`▉`

class Day10(input: List<String>) {

    private val map = input.map { it.map(Char::toChar).map(Pipe.Companion::from).toTypedArray() }.toTypedArray()

    private val start: Coordinate = map.withIndex().firstNotNullOf { (y, pipes) ->
        pipes.withIndex().firstNotNullOfOrNull { (x, pipe) ->
            if (pipe == `▉`) Coordinate(x, y) else null
        }
    }

    @Suppress("EnumEntryName", "NonAsciiCharacters")
    private enum class Pipe(val replacement: Char, val bold: Char, val top: Boolean, val right: Boolean, val bottom: Boolean, val left: Boolean) {
        `│`('|', '║', top = true, right = false, bottom = true, left = false),
        `─`('-', '═', top = false, right = true, bottom = false, left = true),
        `└`('L', '╚', top = true, right = true, bottom = false, left = false),
        `┘`('J', '╝', top = true, right = false, bottom = false, left = true),
        `┐`('7', '╗', top = false, right = false, bottom = true, left = true),
        `┌`('F', '╔', top = false, right = true, bottom = true, left = false),
        ` `('.', ' ', top = false, right = false, bottom = false, left = false),
        `▉`('S', '▉', top = true, right = true, bottom = true, left = true),
        ;

        companion object {
            fun from(char: Char) = Pipe.entries.first { char == it.replacement }
        }
    }

    data class Coordinate(val x: Int, val y: Int)

    private fun Coordinate.filter(predicate: (Pipe) -> Boolean) = takeIf { map.getOrNull(y)?.getOrNull(x)?.let(predicate) ?: false }
    private fun Coordinate.filter(
        path: Set<Coordinate>,
        from: (Pipe) -> Boolean,
        to: (Pipe) -> Boolean,
        transform: (Coordinate) -> Coordinate,
    ) = filter(from)?.let(transform)?.takeIf { it !in path }?.filter(to)

    private fun Coordinate.next(path: Set<Coordinate>): Coordinate? {
        filter(path, from = Pipe::top, to = Pipe::bottom) { copy(y = y - 1) }?.let { return it }
        filter(path, from = Pipe::right, to = Pipe::left) { copy(x = x + 1) }?.let { return it }
        filter(path, from = Pipe::bottom, to = Pipe::top) { copy(y = y + 1) }?.let { return it }
        filter(path, from = Pipe::left, to = Pipe::right) { copy(x = x - 1) }?.let { return it }
        return null
    }

    fun part1() = buildSet<Coordinate> {
        add(start)
        do {
            this += last().next(this) ?: break
        } while (first() != last())
    }.also(::printMap).size / 2

    fun part2(): Int = TODO()

    private fun printMap(path: Set<Coordinate> = emptySet()) = map.withIndex().forEach { (y, pipes) ->
        pipes.withIndex().joinToString("") { (x, pipe) ->
            if (Coordinate(x, y) in path) pipe.bold.toString() else pipe.name
        }.let(::println)
    }

}
