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

import vista.math.Num
import vista.math.numOf
import vista.series.Series

/**
 * Exponential Moving Average (EMA) indicator.
 */
internal class ExponentialMovingAverage(
    private val source: Series,
    private val n: Int,
    private val alpha: Num = numOf(2) / (n + 1)
) : CachedIndicator(source) {

    override val size: Int get() = source.size + 1 - n

    override fun calculate(i: Int): Num {
        if (i == size - 1)
            return sma(source, n)[i]
        return alpha * source[i] + (Num.ONE - alpha) * this[i + 1]
    }
}

/**
 * The exponential moving average, that places a greater weight and significance on the most recent data points.
 *
 * Uses the `alpha = 2 / (n + 1)`.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/trend?id=exponential-moving-average-ema)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.ExponentialMovingAverageTest.emaWithIntSeries
 * @see [sma]
 */
fun ema(source: Series, n: Int = 9): Series = ExponentialMovingAverage(source, n)

/**
 * The exponential moving average used by RSI.
 *
 * Uses the `alpha = 1 / n`.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/trend?id=exponential-moving-average-ema)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.ExponentialMovingAverageTest.rmaWithIntSeries
 * @see [ema]
 */
fun rma(source: Series, n: Int = 9): Series = ExponentialMovingAverage(source, n, Num.ONE.div(n))
