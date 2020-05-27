/*
 * MIT License
 *
 * Copyright (c) 2020 Pablo Pallocchi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package xmas.math

import xmas.math.NaN.value
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * Returns a [Num] converting the given [Int] [value].
 */
fun numOf(value: Int): Num = NumImpl(BigDecimal(value))

/**
 * Returns a [Num] converting the given [Long] [value].
 */
fun numOf(value: Long): Num = NumImpl(BigDecimal(value))

/**
 * Returns a [Num] converting the given [Double] [value].
 */
fun numOf(value: Double): Num = NumImpl(BigDecimal(value))

/**
 * Returns a [Num] converting the given [String] [value].
 */
fun numOf(value: String): Num = NumImpl(BigDecimal(value))

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
     *
     * Note this method should be called using the `+` operator.
     *
     * @sample xmas.math.NumbersTest.plus
     */
    operator fun plus(addend: Num): Num

    /**
     * Returns a [Num] whose value is [value] + [addend].
     *
     * Note this method should be called using the `+` operator.
     *
     * @sample xmas.math.NumbersTest.plus
     */
    operator fun plus(addend: Int): Num = plus(numOf(addend))

    /**
     * Returns a [Num] whose value is [value] - [subtrahend].
     *
     * Note this method should be called using the `-` operator.
     *
     * @sample xmas.math.NumbersTest.minus
     */
    operator fun minus(subtrahend: Num): Num

    /**
     * Returns a [Num] whose value is [value] - [subtrahend].
     *
     * Note this method should be called using the `-` operator.
     *
     * @sample xmas.math.NumbersTest.minus
     */
    operator fun minus(subtrahend: Int): Num = minus(numOf(subtrahend))

    /**
     * Returns a [Num] whose value is [value] * [multiplicand].
     *
     * Note this method should be called using the `*` operator.
     *
     * @sample xmas.math.NumbersTest.times
     */
    operator fun times(multiplicand: Num): Num

    /**
     * Returns a [Num] whose value is [value] * [multiplicand].
     *
     * Note this method should be called using the `*` operator.
     *
     * @sample xmas.math.NumbersTest.times
     */
    operator fun times(multiplicand: Int): Num = times(numOf(multiplicand))

    /**
     * Returns a [Num] whose value is [value] / [divisor].
     *
     * Note this method should be called using the `/` operator.
     *
     * @sample xmas.math.NumbersTest.div
     */
    operator fun div(divisor: Num): Num

    /**
     * Returns a [Num] whose value is [value] / [divisor].
     *
     * Note this method should be called using the `/` operator.
     *
     * @sample xmas.math.NumbersTest.div
     */
    operator fun div(divisor: Int): Num = div(numOf(divisor))

    /**
     * Returns `-1`, `0`, or `1` as current [value] is numerically less than, equal to, or greater than [other].
     *
     * @sample xmas.math.NumbersTest.compare
     */
    operator fun compareTo(other: Num): Int

    /**
     * Returns the rounded [Num] to [n] decimal places, using given rounding [mode].
     *
     * @sample xmas.math.NumbersTest.round
     */
    fun round(n: Int, mode: RoundMode): Num

    /**
     * Returns a [Int] representation.
     *
     * @sample xmas.math.NumbersTest.toInt
     */
    fun toInt(): Int? = value?.toInt()

    /**
     * Returns a [Long] representation.
     *
     * @sample xmas.math.NumbersTest.toLong
     */
    fun toLong(): Long? = value?.toLong()

    /**
     * Returns a [Double] representation.
     *
     * @sample xmas.math.NumbersTest.toDouble
     */
    fun toDouble(): Double? = value?.toDouble()

    /**
     * Returns a [Float] representation.
     *
     * @sample xmas.math.NumbersTest.toFloat
     */
    fun toFloat(): Float? = value?.toFloat()
}

/**
 * [Num] implementation that delegates to a [BigDecimal] instance.
 */
private data class NumImpl(override val value: BigDecimal) : Num {

    override operator fun plus(addend: Num) = addend.value?.let { NumImpl(value.plus(it)) } ?: NaN

    override operator fun minus(subtrahend: Num) = subtrahend.value?.let { NumImpl(value.minus(it)) } ?: NaN

    override operator fun times(multiplicand: Num) = multiplicand.value?.let { NumImpl(value.multiply(it)) } ?: NaN

    override operator fun div(divisor: Num) = divisor.value?.let { NumImpl(value.divide(it)) } ?: NaN

    override operator fun compareTo(other: Num) = other.value?.let { value.compareTo(it) } ?: 0

    override fun round(n: Int, mode: RoundMode): Num = NumImpl(value.setScale(n, mode.value))

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as NumImpl
        if (value.compareTo(other.value) != 0) return false
        return true
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }
}

/**
 * [NaN] in the pine way.
 *
 * @sample xmas.math.NumbersTest.na
 */
typealias na = NaN

/**
 * Not a number.
 *
 * @property value `null` since is not a number
 */
object NaN : Num {

    override val value: BigDecimal? = null

    /**
     * Returns a [NaN] since [NaN] + any [Num] is always [NaN].
     *
     * @sample xmas.math.NumbersTest.plus
     */
    override fun plus(addend: Num): Num = NaN

    /**
     * Returns a [NaN] since [NaN] - any [Num] is always [NaN].
     *
     * @sample xmas.math.NumbersTest.minus
     */
    override fun minus(subtrahend: Num): Num = NaN

    /**
     * Returns a [NaN] since [NaN] * any [Num] is always [NaN].
     *
     * @sample xmas.math.NumbersTest.times
     */
    override fun times(multiplicand: Num): Num = NaN

    /**
     * Returns a [NaN] since [NaN] / any [Num] is always [NaN].
     *
     * @sample xmas.math.NumbersTest.div
     */
    override fun div(divisor: Num): Num = NaN

    /**
     * Returns always `0`.
     *
     * @sample xmas.math.NumbersTest.compare
     */
    override fun compareTo(other: Num): Int = 0

    /**
     * Returns always a [NaN]
     */
    override fun round(n: Int, mode: RoundMode): Num = NaN
}

/**
 * Strategy to round the numbers.
 *
 * @sample xmas.math.NumbersTest.round
 */
enum class RoundMode(val value: RoundingMode) {
    /**
     * Rounding mode to round away from zero.
     */
    UP(RoundingMode.UP),

    /**
     * Rounding mode to round towards zero.
     */
    DOWN(RoundingMode.DOWN),

    /**
     * Rounding mode to round towards "nearest neighbor" unless both neighbors are equidistant, in which case round up.
     */
    HALF_UP(RoundingMode.HALF_UP),

    /**
     * Rounding mode to round towards "nearest neighbor" unless both neighbors are equidistant, in which case round down.
     */
    HALF_DOWN(RoundingMode.HALF_DOWN)
}