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
 * The volume weighted moving average of [source] for [n] bars back, which emphasizes volume
 * by weighing prices based on the amount of trading activity in a given period of time.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/trend?id=volume-weighted-moving-average-vwma)
 *
 * @param source Series of values to process
 * @param volume Series of volume values
 * @param n Number of bars (length)
 * @sample vista.indicators.VolumeWeightedMovingAverageTest.withIntSeries
 * @see [sma]
 */
fun vwma(source: Series, volume: Series, n: Int = 20) = sma(source * volume, n) / sma(volume, n)

/**
 * The volume weighted moving average of close price for [n] bars back, which emphasizes volume
 * by weighing prices based on the amount of trading activity in a given period of time.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/trend?id=volume-weighted-moving-average-vwma)
 *
 * @param n Number of bars (length)
 * @sample vista.indicators.VolumeWeightedMovingAverageTest.withMarketData
 * @see [sma]
 */
fun Data.vwma(n: Int = 20) = vwma(close, volume, n)