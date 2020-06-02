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

package insight.indicators

import insight.math.Num
import insight.math.pow
import insight.series.Series

/**
 * Standard deviation indicator.
 */
internal class StandardDeviation(
    private val source: Series,
    private val n: Int
) : CachedIndicator(source) {

    override val size: Int get() = source.size + 1 - n

    private val mean = sma(source, n)

    override fun calculate(i: Int): Num {
        val diff = source - mean[i]
        val pow = pow(diff, 2)
        return sma(pow, n)[i].sqrt()
    }
}

/**
 * The standard deviation of [source] for [n] bars back.
 *
 * **See:** [TradingView](https://www.tradingview.com/pine-script-reference/#fun_stdev)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample insight.indicators.StandardDeviationTest.withIntSeries
 * @see [dev]
 */
fun stdev(source: Series, n: Int): Series = StandardDeviation(source, n)