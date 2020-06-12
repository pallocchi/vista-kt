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

package vista.indicators

import vista.data.Data
import vista.math.Num
import vista.math.abs
import vista.series.Series

/**
 * Deviation indicator.
 */
internal class Deviation(
    private val source: Series,
    private val n: Int
) : CachedIndicator(source) {

    override val size: Int get() = source.size + 1 - n

    private val mean = sma(source, n)

    override fun calculate(i: Int): Num = sma(abs((source - mean[i])), n)[i]
}

/**
 * The deviation of [source] for [n] bars back.
 *
 * Measures the difference between the series and it's [sma].
 *
 * **See:** [TradingView](https://www.tradingview.com/pine-script-reference/#fun_dev)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.DeviationTest.withIntSeries
 * @see [stdev]
 */
fun dev(source: Series, n: Int): Series = Deviation(source, n)

/**
 * The deviation of close price for [n] bars back.
 *
 * Measures the difference between the series and it's [sma].
 *
 * **See:** [TradingView](https://www.tradingview.com/pine-script-reference/#fun_dev)
 *
 * @param n Number of bars (length)
 * @sample vista.indicators.DeviationTest.withMarketData
 * @see [stdev]
 */
fun Data.dev(n: Int): Series = Deviation(close, n)