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
import vista.math.change
import vista.series.Series

/**
 * The rate of change, which is the difference between current value of [source] and the value of [source] that was [n] days ago.
 *
 * **See:** [TradingView](https://www.tradingview.com/pine-script-reference/#fun_roc)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.RateOfChangeTest.withIntSeries
 */
fun roc(source: Series, n: Int = 9) = change(source, n) * 100 / source(n)

/**
 * The rate of change, which is the difference between current close price and the value that was [n] days ago.
 *
 * **See:** [TradingView](https://www.tradingview.com/pine-script-reference/#fun_roc)
 *
 * @param n Number of bars (length)
 * @sample vista.indicators.RateOfChangeTest.withMarketData
 */
fun Data.roc(n: Int = 9) = roc(close, n)