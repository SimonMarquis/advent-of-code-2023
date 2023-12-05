class Day05(private val input: List<String>) {

    private val seeds = input.first().substringAfter(":").trim().split(" ").map(String::toLong).also(::println)
    private val almanac = input.asSequence().drop(2).foldInPlace(mutableListOf<Conversion>()) { line: String ->
        when (line.firstOrNull()) {
            in 'a'..'z' -> add(line.toConversion())
            in '0'..'9' -> last().instructions.add(line.toInstruction())
        }
    }.onEach(::println)

    private fun String.toConversion() = split("-to-", " ").let { (src, dst) -> Conversion(src, dst) }
    private fun String.toInstruction() = split(" ").map { it.toLong() }.let { (src, dst, length) -> Instruction(dst, src, length) }

    private data class Conversion(val src: String, val dst: String, val instructions: MutableList<Instruction> = mutableListOf()) {
        operator fun contains(number: Long) = instructions.any { number in it }
    }

    private data class Instruction(val src: Long, val dst: Long, val length: Long) {
        operator fun contains(number: Long) = number in (src..<src + length)
        fun map(number: Long): Long = number + dst - src
    }

    fun part1(): Long = seeds.minOf { seed ->
        almanac.fold(seed) { acc, conversion ->
            conversion.instructions.singleOrNull { acc in it }?.map(acc) ?: acc
        }
    }

    fun part2(): Long = TODO()

}
