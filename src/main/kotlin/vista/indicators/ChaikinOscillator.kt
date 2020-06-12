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
 * The Chaikin oscillator, which measures the accumulation-distribution line of [macd].
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volume?id=chainkin-oscillator)
 *
 * @param close Series of close prices
 * @param high Series of high prices
 * @param low Series of low prices
 * @param volume Series of volumes
 * @param fastLength Number of bars (length) used by the fast [ema]
 * @param slowLength Number of bars (length) used by the slow [ema]
 * @sample vista.indicators.ChaikinOscillatorTest.withIntSeries
 */
fun chaikin(
    close: Series,
    high: Series,
    low: Series,
    volume: Series,
    fastLength: Int = 3,
    slowLength: Int = 10
): Series {
    val accdist = accdist(close, high, low, volume)
    return ema(accdist, fastLength) - ema(accdist, slowLength)
}

/**
 * The Chaikin oscillator, which measures the accumulation-distribution line of [macd].
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volume?id=chainkin-oscillator)
 *
 * @param fastLength Number of bars (length) used by the fast [ema]
 * @param slowLength Number of bars (length) used by the slow [ema]
 * @sample vista.indicators.ChaikinOscillatorTest.withMarketData
 */
fun Data.chaikin(fastLength: Int = 3, slowLength: Int = 10) = chaikin(close, high, low, volume, fastLength, slowLength)