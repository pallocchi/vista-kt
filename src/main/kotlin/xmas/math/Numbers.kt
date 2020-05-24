package xmas.math

import java.math.BigDecimal

/**
 * Returns a [Num] converting the given [Int] [value].
 */
fun numOf(value: Int): Num = Num.Impl(BigDecimal(value))

/**
 * Returns a [Num] converting the given [Long] [value].
 */
fun numOf(value: Long): Num = Num.Impl(BigDecimal(value))

/**
 * Returns a [Num] converting the given [Double] [value].
 */
fun numOf(value: Double): Num = Num.Impl(BigDecimal(value))

/**
 * Returns a [Num] converting the given [String] [value].
 */
fun numOf(value: String): Num = Num.Impl(BigDecimal(value))

/**
 * Immutable, arbitrary-precision signed decimal numbers.
 *
 * @property value Precision number value
 * @see BigDecimal
 */
interface Num {

    val value: BigDecimal?

    /**
     * Returns a [Num] whose value is [value] + [addend].
     */
    operator fun plus(addend: Num): Num

    /**
     * Returns a [Num] whose value is [value] - [subtrahend].
     */
    operator fun minus(subtrahend: Num): Num

    /**
     * Returns a [Num] whose value is [value] * [multiplicand].
     */
    operator fun times(multiplicand: Num): Num

    /**
     * Returns a [Num] whose value is [value] / [divisor].
     */
    operator fun div(divisor: Num): Num

    /**
     * Returns `-1`, `0`, or `1` as current [value] is numerically less than, equal to, or greater than [other].
     */
    operator fun compareTo(other: Num): Int

    /**
     * [Num] implementation that delegates to a [BigDecimal] instance.
     */
    class Impl(override val value: BigDecimal) : Num {

        override operator fun plus(addend: Num) = addend.value?.let { Impl(value + it) } ?: NaN

        override operator fun minus(subtrahend: Num) = subtrahend.value?.let { Impl(value - it) } ?: NaN

        override operator fun times(multiplicand: Num) = multiplicand.value?.let { Impl(value * it) } ?: NaN

        override operator fun div(divisor: Num) = divisor.value?.let { Impl(value / it) } ?: NaN

        override operator fun compareTo(other: Num) = other.value?.let { value.compareTo(it) } ?: 0
    }
}

/**
 * Not a number.
 *
 * @property value `null` since is not a number
 */
object NaN : Num {

    override val value: BigDecimal? = null

    /**
     * Returns a [NaN] since [NaN] + any [Num] is always [NaN].
     */
    override fun plus(addend: Num): Num = NaN

    /**
     * Returns a [NaN] since [NaN] - any [Num] is always [NaN].
     */
    override fun minus(subtrahend: Num): Num = NaN

    /**
     * Returns a [NaN] since [NaN] * any [Num] is always [NaN].
     */
    override fun times(multiplicand: Num): Num = NaN

    /**
     * Returns a [NaN] since [NaN] / any [Num] is always [NaN].
     */
    override fun div(divisor: Num): Num = NaN

    /**
     * Returns always `0`.
     */
    override fun compareTo(other: Num): Int = 0
}
