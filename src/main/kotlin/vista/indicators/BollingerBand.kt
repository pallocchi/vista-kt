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
import vista.series.Series

/**
 * The Bollinger Bands, defined as two standard deviations (positively and negatively) away from the [sma].
 *
 * Returns a triple of middle, upper and lower lines.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volatility?id=bollinger-bands%c2%ae)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @param factor Standard deviation factor
 * @sample vista.indicators.BollingerBandTest.withIntSeries
 * @see [sma] [stdev]
 */
fun bb(
    source: Series,
    n: Int = 20,
    factor: Int = 2
): Triple<Series, Series, Series> {
    val basis = sma(source, n)
    val dev = stdev(source, n) * factor
    val upper = basis + dev
    val lower = basis - dev
    return Triple(basis, upper, lower)
}

/**
 * The Bollinger Bands, defined as two standard deviations (positively and negatively) away from the [sma].
 *
 * Returns a triple of middle, upper and lower lines.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volatility?id=bollinger-bands%c2%ae)
 *
 * @param n Number of bars (length)
 * @param factor Standard deviation factor
 * @sample vista.indicators.BollingerBandTest.withMarketData
 * @see [sma] [stdev]
 */
fun Data.bb(n: Int = 20, factor: Int = 2) = bb(close, n, factor)