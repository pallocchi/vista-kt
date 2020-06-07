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

package vista.series

import vista.math.NaN
import vista.math.Num
import vista.math.min
import vista.math.numOf

/**
 * Returns a [Num] series from given [values] range.
 */
fun seriesOf(values: IntRange): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Returns a [Num] series from given [Int] values.
 */
fun seriesOf(vararg values: Int): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Returns a [Num] series from given [values].
 */
fun seriesOf(vararg values: Num): Series = SimpleSeries(mutableListOf(*values))

/**
 * Returns a [Num] series from given [Long] values.
 */
fun seriesOf(vararg values: Long): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Returns a [Num] series from given [Double] values.
 */
fun seriesOf(vararg values: Double): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Returns a [Num] series from given [String] values.
 */
fun seriesOf(vararg values: String): Series = SimpleSeries(values.map { numOf(it) }.toMutableList())

/**
 * Series of numbers.
 */
interface Series {
    /**
     * Returns the latest index.
     */
    val time: Int

    /**
     * Returns the series size.
     */
    val size: Int

    /**
     * Returns the latest value in the series (same as `series[0]`).
     */
    val last: Num get() = this[0]

    /**
     * Returns the previous value in the series (same as `series[1]`).
     */
    val prev: Num get() = this[1]

    /**
     * Returns the series value [i] bars from now.
     *
     * @sample vista.series.SeriesTest.withIndexingOperator
     */
    operator fun get(i: Int): Num

    /**
     * Returns a [Series] whose vales are moved by [i] positions.
     */
    operator fun invoke(i: Int): Series = ShiftedSeries(this, i)

    /**
     * Returns a [Series] whose values are the current ones `+` [other].
     *
     * @sample vista.series.SeriesTest.plus
     */
    operator fun plus(other: Series): Series = BiCalculatedSeries(this, other) { x, y -> x + y }

    /**
     * Returns a [Series] whose values are the current ones `+` [other].
     *
     * @sample vista.series.SeriesTest.plus
     */
    operator fun plus(other: Num): Series = CalculatedSeries(this) { it + other }

    /**
     * Returns a [Series] whose values are the current ones `+` [other].
     *
     * @sample vista.series.SeriesTest.plus
     */
    operator fun plus(other: Int): Series = plus(numOf(other))

    /**
     * Returns a [Series] whose values are the current ones `+` [other].
     *
     * @sample vista.series.SeriesTest.plus
     */
    operator fun plus(other: Double): Series = plus(numOf(other))

    /**
     * Returns a [Series] whose values are the current ones `-` [other].
     *
     * @sample vista.series.SeriesTest.minus
     */
    operator fun minus(other: Series): Series = BiCalculatedSeries(this, other) { x, y -> x - y }

    /**
     * Returns a [Series] whose values are the current ones `-` [other].
     *
     * @sample vista.series.SeriesTest.minus
     */
    operator fun minus(other: Num): Series = CalculatedSeries(this) { it - other }

    /**
     * Returns a [Series] whose values are the current ones `-` [other].
     *
     * @sample vista.series.SeriesTest.minus
     */
    operator fun minus(other: Int): Series = minus(numOf(other))

    /**
     * Returns a [Series] whose values are the current ones `-` [other].
     *
     * @sample vista.series.SeriesTest.minus
     */
    operator fun minus(other: Double): Series = minus(numOf(other))

    /**
     * Returns a [Series] whose values are the current ones `*` [other].
     *
     * @sample vista.series.SeriesTest.times
     */
    operator fun times(other: Series): Series = BiCalculatedSeries(this, other) { x, y -> x * y }

    /**
     * Returns a [Series] whose values are the current ones `*` [other].
     *
     * @sample vista.series.SeriesTest.times
     */
    operator fun times(other: Num): Series = CalculatedSeries(this) { it * other }

    /**
     * Returns a [Series] whose values are the current ones `*` [other].
     *
     * @sample vista.series.SeriesTest.times
     */
    operator fun times(other: Int): Series = times(numOf(other))

    /**
     * Returns a [Series] whose values are the current ones `*` [other].
     *
     * @sample vista.series.SeriesTest.times
     */
    operator fun times(other: Double): Series = times(numOf(other))

    /**
     * Returns a [Series] whose values are the current ones `/` [other].
     *
     * @sample vista.series.SeriesTest.div
     */
    operator fun div(other: Series): Series = BiCalculatedSeries(this, other) { x, y -> x / y }

    /**
     * Returns a [Series] whose values are the current ones `/` [other].
     *
     * @sample vista.series.SeriesTest.div
     */
    operator fun div(other: Num): Series = CalculatedSeries(this) { it / other }

    /**
     * Returns a [Series] whose values are the current ones `/` [other].
     *
     * @sample vista.series.SeriesTest.div
     */
    operator fun div(other: Int): Series = div(numOf(other))

    /**
     * Returns a [Series] whose values are the current ones `/` [other].
     *
     * @sample vista.series.SeriesTest.div
     */
    operator fun div(other: Double): Series = div(numOf(other))

    /**
     * Returns `-1`, `0`, or `1` as [last] value is numerically less than, equal to, or greater than [other].
     *
     * @sample vista.series.SeriesTest.compareTo
     */
    operator fun compareTo(other: Series): Int = this.last.compareTo(other.last)
}

/**
 * Returns if two series has crossed each other.
 *
 * @sample vista.series.SeriesTest.cross
 */
infix fun Series.cross(other: Series) = this crossOver other || this crossUnder other

/**
 * Returns if current series has crossed over the [other].
 *
 * @sample vista.series.SeriesTest.crossOver
 */
infix fun Series.crossOver(other: Series) = this.last > other.last && this.prev < other.prev

/**
 * Returns if current series has crossed under the [other].
 *
 * @sample vista.series.SeriesTest.crossUnder
 */
infix fun Series.crossUnder(other: Series) = this.last < other.last && this.prev > other.prev

/**
 * Series of numbers.
 */
private class SimpleSeries(private val values: List<Num>) : Series {

    override val time: Int get() = values.size - 1

    override val size: Int get() = values.size

    override operator fun get(i: Int): Num {
        val rIndex = size - i - 1
        if (rIndex in 0..size)
            return values[rIndex]
        return NaN
    }
}


/**
 * Series that performs an [operation] over the values of [source].
 */
internal class CalculatedSeries(
    private val source: Series,
    private val operation: (Num) -> Num
) : Series {

    override val time: Int get() = source.time

    override val size: Int get() = source.size

    override fun get(i: Int) = operation(source[i])
}

/**
 * Series that performs an [operation] over the values of sources [x] and [y].
 */
internal class BiCalculatedSeries(
    private val x: Series,
    private val y: Series,
    private val operation: (Num, Num) -> Num
) : Series {

    override val time: Int get() = min(x.time, y.time)

    override val size: Int get() = min(x.size, y.size)

    override fun get(i: Int) = operation(x[i], y[i])
}

/**
 * Series that has been shifted by [n] bars.
 */
private class ShiftedSeries(
    private val source: Series,
    private val n: Int
) : Series {

    override val time: Int get() = source.time

    override val size: Int get() = source.size - n

    override fun get(i: Int) = source[i + n]
}