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
import vista.math.numOf
import vista.series.Series

/**
 * Simple Moving Average (SMA) indicator.
 */
internal class SimpleMovingAverage(
    private val source: Series,
    private val n: Int
) : Indicator(source) {

    override val size: Int get() = source.size + 1 - n

    override fun calculate(i: Int): Num {
        var sum = Num.ZERO
        for (j in 0 until n)
            sum += source[i + j]
        return sum / numOf(n)
    }
}

/**
 * The simple moving average, that is the sum of last [n] values of [source], divided by [n].
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/trend?id=simple-moving-average-sma)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.SimpleMovingAverageTest.withIntSeries
 */
fun sma(source: Series, n: Int = 9): Series = if (n > 1) SimpleMovingAverage(source, n) else source

/**
 * The simple moving average, that is the sum of last [n] close prices, divided by [n].
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/trend?id=simple-moving-average-sma)
 *
 * @param n Number of bars (length)
 * @sample vista.indicators.SimpleMovingAverageTest.withMarketData
 */
fun Data.sma(n: Int = 9): Series = sma(close, n)