inline fun <T, R> Sequence<T>.foldInPlace(
    initial: R,
    operation: R.(T) -> Unit,
): R = fold(initial) { acc: R, t: T -> acc.apply { operation(t) } }

inline fun <T, R> Sequence<T>.foldIndexedInPlace(
    initial: R,
    operation: R.(index: Int, T) -> Unit,
): R = foldIndexed(initial) { index: Int, acc: R, t: T -> acc.apply { operation(index, t) } }

