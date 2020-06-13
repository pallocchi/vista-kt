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
import vista.math.abs
import vista.math.max
import vista.math.min
import vista.series.Series

/**
 * True range indicator.
 */
internal class TrueRange(
    private val close: Series,
    private val high: Series,
    private val low: Series
) : CachedIndicator(close) {

    override val size: Int get() = min(close.size, high.size, low.size)

    private val hl = high - low

    private val tr = max(hl, abs(high - close(1)), abs(low - close(1)))

    override fun calculate(i: Int) = if (i == size - 1) hl[i] else tr[i]
}

/**
 * The true range, used to calculate the [atr] indicator.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volatility?id=average-true-range-atr)
 *
 * @param close Series of values to process
 * @param high Series of high values
 * @param low Series of low values
 * @sample vista.indicators.StochasticOscillatorTest.withIntSeries
 */
fun tr(close: Series, high: Series, low: Series): Series = TrueRange(close, high, low)

/**
 * The true range, used to calculate the [atr] indicator.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volatility?id=average-true-range-atr)
 *
 * @sample vista.indicators.StochasticOscillatorTest.withMarketData
 */
fun Data.tr(): Series = tr(close, high, low)

/**
 * The average true range for [n] periods back.
 *
 * Returns the [rma] of [tr] (true range).
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volatility?id=average-true-range-atr)
 *
 * @param close Series of values to process
 * @param high Series of high values
 * @param low Series of low values
 * @sample vista.indicators.StochasticOscillatorTest.withIntSeries
 */
fun atr(close: Series, high: Series, low: Series, n: Int = 14) = rma(tr(close, high, low), n)

/**
 * The average true range for [n] periods back.
 *
 * Returns the [rma] of [tr] (true range).
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volatility?id=average-true-range-atr)
 *
 * @sample vista.indicators.StochasticOscillatorTest.withMarketData
 */
fun Data.atr(n: Int = 14) = atr(close, high, low, n)