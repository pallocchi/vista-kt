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

import xmas.series.Series
import xmas.series.StaticSeries

/**
 * Returns the lowest of [x] and [y].
 */
fun min(x: Int, y: Int): Int = if (x <= y) x else y

/**
 * Returns the greatest of [x] and [y].
 */
fun max(x: Int, y: Int): Int = if (x >= y) x else y

/**
 * Returns the lowest of [x] and [y].
 */
fun min(x: Num, y: Num): Num = if (x <= y) x else y

/**
 * Returns the greatest of [x] and [y].
 */
fun max(x: Num, y: Num): Num = if (x >= y) x else y

/**
 * Returns a series with the lowest of [x] and [y].
 *
 * @sample xmas.series.SeriesTest.min
 */
fun min(x: Series, y: Series): Series = MinSeries(x, y)

/**
 * Returns a series with the greatest of [x] and [y].
 *
 * @sample xmas.series.SeriesTest.max
 */
fun max(x: Series, y: Series): Series = MaxSeries(x, y)

/**
 * Returns a series with the lowest of [x] and [y].
 *
 * @sample xmas.series.SeriesTest.min
 */
fun min(x: Series, y: Num): Series = MinSeries(x, StaticSeries(y))

/**
 * Returns a series with the greatest of [x] and [y].
 *
 * @sample xmas.series.SeriesTest.max
 */
fun max(x: Series, y: Num): Series = MaxSeries(x, StaticSeries(y))

/**
 * Series that returns the max value in each index.
 */
private class MaxSeries(
    private val x: Series,
    private val y: Series
) : Series() {

    override val time: Int get() = min(x.time, y.time)

    override val size: Int get() = min(x.size, y.size)

    override fun get(i: Int) = max(x[i], y[i])
}

/**
 * Series that returns the min value in each index.
 */
private class MinSeries(
    private val x: Series,
    private val y: Series
) : Series() {

    override val time: Int get() = min(x.time, y.time)

    override val size: Int get() = min(x.size, y.size)

    override fun get(i: Int) = min(x[i], y[i])
}