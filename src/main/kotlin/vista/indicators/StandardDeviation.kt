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
import vista.math.pow
import vista.series.Series

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
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volatility?id=standard-deviation)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.StandardDeviationTest.withIntSeries
 * @see [dev]
 */
fun stdev(source: Series, n: Int): Series = StandardDeviation(source, n)

/**
 * The standard deviation of close price for [n] bars back.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volatility?id=standard-deviation)
 *
 * @param n Number of bars (length)
 * @sample vista.indicators.StandardDeviationTest.withMarketData
 * @see [dev]
 */
fun Data.stdev(n: Int): Series = stdev(close, n)