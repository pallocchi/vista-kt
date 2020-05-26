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
     *
     * @sample xmas.math.NumbersTest.plus
     */
    operator fun plus(addend: Num): Num

    /**
     * Returns a [Num] whose value is [value] - [subtrahend].
     *
     * @sample xmas.math.NumbersTest.minus
     */
    operator fun minus(subtrahend: Num): Num

    /**
     * Returns a [Num] whose value is [value] * [multiplicand].
     *
     * @sample xmas.math.NumbersTest.times
     */
    operator fun times(multiplicand: Num): Num

    /**
     * Returns a [Num] whose value is [value] / [divisor].
     *
     * @sample xmas.math.NumbersTest.div
     */
    operator fun div(divisor: Num): Num

    /**
     * Returns `-1`, `0`, or `1` as current [value] is numerically less than, equal to, or greater than [other].
     *
     * @sample xmas.math.NumbersTest.compare
     */
    operator fun compareTo(other: Num): Int

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

    /**
     * [Num] implementation that delegates to a [BigDecimal] instance.
     */
    data class Impl(override val value: BigDecimal) : Num {

        override operator fun plus(addend: Num) = addend.value?.let { Impl(value.plus(it)) } ?: NaN

        override operator fun minus(subtrahend: Num) = subtrahend.value?.let { Impl(value.minus(it)) } ?: NaN

        override operator fun times(multiplicand: Num) = multiplicand.value?.let { Impl(value.multiply(it)) } ?: NaN

        override operator fun div(divisor: Num) = divisor.value?.let { Impl(value.divide(it)) } ?: NaN

        override operator fun compareTo(other: Num) = other.value?.let { value.compareTo(it) } ?: 0
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
}
