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

package insight.math

import insight.series.BiCalculatedSeries
import insight.series.CalculatedSeries
import insight.series.Series

/**
 * Returns the lowest of [x] and [y].
 *
 * @sample insight.math.FunctionsTest.minWithInt
 */
fun min(x: Int, y: Int): Int = if (x <= y) x else y

/**
 * Returns the greatest of [x] and [y].
 *
 * @sample insight.math.FunctionsTest.maxWithNum
 */
fun max(x: Int, y: Int): Int = if (x >= y) x else y

/**
 * Returns the lowest of [x] and [y].
 *
 * @sample insight.math.FunctionsTest.minWithNum
 */
fun min(x: Num, y: Num): Num = if (x < y) x else y

/**
 * Returns the greatest of [x] and [y].
 *
 * @sample insight.math.FunctionsTest.maxWithNum
 */
fun max(x: Num, y: Num): Num = if (x > y) x else y

/**
 * Returns a series with the lowest of [x] and [y].
 *
 * @sample insight.math.FunctionsTest.minWithSeries
 */
fun min(x: Series, y: Series): Series = BiCalculatedSeries(x, y) { a, b -> min(a, b) }

/**
 * Returns a series with the greatest of [x] and [y].
 *
 * @sample insight.math.FunctionsTest.maxWithSeries
 */
fun max(x: Series, y: Series): Series = BiCalculatedSeries(x, y) { a, b -> max(a, b) }

/**
 * Returns a series with the lowest of [x] and [y].
 *
 * @sample insight.math.FunctionsTest.minWithSeries
 */
fun min(x: Series, y: Num): Series = CalculatedSeries(x) { a -> min(a, y) }

/**
 * Returns a series with the greatest of [x] and [y].
 *
 * @sample insight.math.FunctionsTest.maxWithSeries
 */
fun max(x: Series, y: Num): Series = CalculatedSeries(x) { a -> max(a, y) }

/**
 * Returns a series whose values are the square root of the current ones.
 *
 * @sample insight.math.FunctionsTest.sqrtWithSeries
 */
fun sqrt(x: Series): Series = CalculatedSeries(x) { it.sqrt() }

/**
 * Returns a series whose values are the current ones raised to the power of [n].
 *
 * @sample insight.math.FunctionsTest.powWithSeries
 */
fun pow(x: Series, n: Int): Series = CalculatedSeries(x) { it.pow(n) }

/**
 * Returns a series whose values are the absolute value of the current ones.
 *
 * @sample insight.math.FunctionsTest.absWithSeries
 */
fun abs(x: Series): Series = CalculatedSeries(x) { if (it >= Num.ZERO) it else -it }