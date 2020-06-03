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
 * The stochastic oscillator of [source] for [k] bars back, to evaluate overbought or oversold conditions.
 *
 * Returns a pair of %K and %D series.
 *
 * **See:** [TradingView](https://www.tradingview.com/pine-script-reference/#fun_stoch)
 *
 * @param source Series of values to process
 * @param high Series of high values
 * @param low Series of low values
 * @param k Number of bars (length) for %K
 * @param d Number of bars (length) for %D moving average
 * @param smooth Smooth for %K
 * @sample vista.indicators.StochasticOscillatorTest.withIntSeries
 */
fun stoch(
    source: Series,
    high: Series,
    low: Series,
    k: Int = 14,
    d: Int = 3,
    smooth: Int = 3
): Pair<Series, Series> {
    val lowest = lowest(low, k)
    val highest = highest(high, k)
    val kLine = ((source - lowest) / (highest - lowest)) * 100
    val kLineSmoothed = sma(kLine, smooth)
    val dLine = sma(kLineSmoothed, d)
    return Pair(kLineSmoothed, dLine)
}