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

package xmas.series

import xmas.math.NaN
import xmas.math.Num
import xmas.math.numOf

/**
 * Returns a [Num] series from given [values].
 */
fun seriesOf(vararg values: Num): Series = SimpleSeries(mutableListOf(*values))

/**
 * Returns a [Num] series from given [Int] values.
 */
fun seriesOf(vararg values: Int): Series = SimpleSeries(values.map { numOf(it) })

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
fun seriesOf(vararg values: String): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Series of numbers.
 */
abstract class Series {

    /**
     * Returns the series size.
     */
    abstract fun size(): Int

    /**
     * Returns the series value at given index.
     */
    abstract operator fun get(i: Int): Num
}

/**
 * Returns if two series has crossed each other.
 *
 * @sample xmas.series.SeriesTest.cross
 */
infix fun Series.cross(other: Series) = this crossOver other || this crossUnder other

/**
 * Returns if current series has crossed over the [other].
 *
 * @sample xmas.series.SeriesTest.crossOver
 */
infix fun Series.crossOver(other: Series) = this[0] > other[0] && this[1] < other[1]

/**
 * Returns if current series has crossed under the [other].
 *
 * @sample xmas.series.SeriesTest.crossUnder
 */
infix fun Series.crossUnder(other: Series) = this[0] < other[0] && this[1] > other[1]

/**
 * Basic series of numbers implementation.
 */
internal class SimpleSeries(private val values: List<Num>) : Series() {

    override fun size(): Int = values.size

    override operator fun get(i: Int): Num {
        val index = size() - i - 1
        if (index in 0..size())
            return values[index]
        return NaN
    }
}
