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
import vista.series.Series

/**
 * Weighted Moving Average (WMA) indicator.
 */
internal class WeightedMovingAverage(
    private val source: Series,
    private val n: Int
) : Indicator(source) {

    override val size: Int get() = source.size + 1 - n

    override fun calculate(i: Int): Num {
        var norm = Num.ZERO
        var sum = Num.ZERO
        for (j in 0 until n) {
            val weight = (n - j) * n
            norm += weight
            sum += source[i + j] * weight
        }
        return sum / norm
    }
}

/**
 * The weighted moving average of [source] for [n] bars back.
 * In wma weighting factors decrease in arithmetical progression.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/trend?id=weighted-moving-average-wma)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.WeightedMovingAverageTest.withIntSeries
 * @see [sma]
 */
fun wma(source: Series, n: Int = 9): Series = WeightedMovingAverage(source, n)

/**
 * The weighted moving average of close price for [n] bars back.
 * In wma weighting factors decrease in arithmetical progression.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/trend?id=weighted-moving-average-wma)
 *
 * @param n Number of bars (length)
 * @sample vista.indicators.WeightedMovingAverageTest.withMarketData
 * @see [sma]
 */
fun Data.wma(n: Int = 9): Series = wma(close, n)
