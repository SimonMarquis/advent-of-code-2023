class Day08(input: List<String>) {

    private val instructions = input.first()
    private val network = input.drop(2)
        .map { it.split("""\W+""".toRegex()) }
        .associateBy({ it.first() }, { (id, left, right) -> Node(id, left, right) })

    data class Node(val id: String, val left: String, val right: String)

    private fun Node.next(instruction: Char): Node = when (instruction) {
        'L' -> left
        'R' -> right
        else -> error(instruction)
    }.let(network::getValue)

    fun part1(): Int {
        var node = network.getValue("AAA")
        return instructions.asSequence().infinite().indexOfFirst {
            node.next(it).also { next ->
                node = network[next.id]!!
            }.id == "ZZZ"
        }.inc()
    }

    fun part2(): Long = buildMap stepsByNodes@{
        val nodes = network.values.filter { it.id.endsWith('A') }.map { it to it }.toMutableList()
        instructions.asSequence().infinite().forEachIndexed { index, instruction ->
            nodes.replaceAll { (start, current) -> start to current.next(instruction) }
            nodes.removeAll { (start, current) -> current.id.endsWith('Z').also { if (it) put(start, index.inc()) } }
            if (nodes.isEmpty()) return@stepsByNodes
        }
    }.map { it.value.toLong() }.reduce(::lcm)

}
