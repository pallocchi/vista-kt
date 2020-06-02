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

import vista.series.Series

/**
 * The moving average convergence/divergence of [source], to evaluate strength, direction, momentum, and duration of a trend.
 *
 * Returns a triple of MACD line, signal line and histogram line.
 *
 * **See:** [TradingView](https://www.tradingview.com/pine-script-reference/#fun_macd)
 *
 * @param source Series of values to process
 * @param fastLength Number of bars (length) used by the fast [sma]
 * @param slowLength Number of bars (length) used by the slow [sma]
 * @param slowLength Number of bars (length) used by the signal line
 * @sample vista.indicators.MovingAverageConvergenceDivergenceTest.withIntSeries
 * @see [sma]
 */
fun macd(
    source: Series,
    fastLength: Int = 12,
    slowLength: Int = 26,
    signalLength: Int = 9
): Triple<Series, Series, Series> {
    val macd = ema(source, fastLength) - ema(source, slowLength)
    val signal = ema(macd, signalLength)
    val histogram = macd - signal
    return Triple(macd, signal, histogram)
}