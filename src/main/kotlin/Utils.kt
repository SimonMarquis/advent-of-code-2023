import kotlin.math.absoluteValue

fun <T> Sequence<T>.infinite() = sequence { while (true) yieldAll(this@infinite) }

fun gcd(a: Long, b: Long): Long = if (b == 0L) a.absoluteValue else gcd(b, a % b)

fun lcm(a: Long, b: Long) = (a * b) / gcd(a, b)

inline fun <T, R> Sequence<T>.foldInPlace(
    initial: R,
    operation: R.(T) -> Unit,
): R = fold(initial) { acc: R, t: T -> acc.apply { operation(t) } }

inline fun <T, R> Sequence<T>.foldIndexedInPlace(
    initial: R,
    operation: R.(index: Int, T) -> Unit,
): R = foldIndexed(initial) { index: Int, acc: R, t: T -> acc.apply { operation(index, t) } }

@JvmName("intProduct")
fun Sequence<Int>.product(): Long = fold(1L, Long::times)

@JvmName("longProduct")
fun Sequence<Long>.product(): Long = fold(1L, Long::times)
