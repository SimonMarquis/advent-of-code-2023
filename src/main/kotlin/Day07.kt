import Day07.Card.J

class Day07(private val input: List<String>) {

    private fun String.toHand() = split(" ").let { (cards, bid) ->
        Hand(
            cards = cards.map(Char::toString).map(Card::valueOf),
            bid = bid.toLong(),
        )
    }

    private data class Hand(val cards: List<Card>, val bid: Long) : Comparable<Hand> {
        val type = cards.groupingBy { it }.eachCount().values.let {
            when {
                5 in it -> Type.`Five of a kind`
                4 in it -> Type.`Four of a kind`
                3 in it && 2 in it -> Type.`Full house`
                3 in it -> Type.`Three of a kind`
                it.count { it == 2 } == 2 -> Type.`Two pair`
                2 in it -> Type.`One pair`
                else -> Type.`High card`
            }
        }

        override fun compareTo(other: Hand): Int = type.compareTo(other.type).takeUnless { it == 0 }?.unaryMinus()
            ?: cards.zip(other.cards).firstNotNullOfOrNull { (l, r) -> l.compareTo(r).unaryMinus().takeUnless { it == 0 } }
            ?: 0
    }

    private data class JokerHand(val hand: Hand) : Comparable<JokerHand> {
        val joker: Hand = hand.asJoker()
        private fun List<Card>.asJoker() = map { it.takeUnless { it == J } ?: Card.`0` }
        private fun Hand.asJoker(): Hand =
            if (J !in this.cards) this
            else Card.entries.minOf { r -> copy(cards = cards.map { if (it == J) r else it }) }

        override fun compareTo(other: JokerHand): Int = joker.type.compareTo(other.joker.type).takeUnless { it == 0 }?.unaryMinus()
            ?: hand.cards.asJoker().zip(other.hand.cards.asJoker()).firstNotNullOfOrNull { (l, r) -> r.compareTo(l).takeUnless { it == 0 } }
            ?: 0
    }

    @Suppress("EnumEntryName")
    enum class Card { `0`, `2`, `3`, `4`, `5`, `6`, `7`, `8`, `9`, T, J, Q, K, A }

    @Suppress("EnumEntryName")
    enum class Type { `High card`, `One pair`, `Two pair`, `Three of a kind`, `Full house`, `Four of a kind`, `Five of a kind` }

    fun part1(): Long = input
        .map { it.toHand() }
        .sorted()
        .asReversed()
        .foldIndexed(0L) { index, acc, hand -> acc + (index + 1) * hand.bid }

    fun part2(): Long = input
        .map { it.toHand() }
        .map(::JokerHand)
        .sorted()
        .asReversed()
        .foldIndexed(0L) { index, acc, hand -> acc + (index + 1) * hand.joker.bid }

}
