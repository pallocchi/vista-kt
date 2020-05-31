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

package xmas.indicators

import xmas.math.NaN
import xmas.math.Num
import xmas.series.Series
import xmas.series.max

/**
 * Relative strength index (RSI) indicator.
 */
internal class RelativeStrengthIndex(
    private val source: Series,
    private val n: Int
) : Indicator(source) {

    companion object {
        val MIN_VALUE = Num.ZERO
        val MAX_VALUE = Num.HUNDRED
    }

    override val size: Int get() = source.size - n

    private val upward = rma(max(source - source(1), Num.ZERO), n)
    private val downward = rma(max(source(1) - source, Num.ZERO), n)

    override fun calculate(index: Int): Num {
        if (index in 0..size) {
            if (downward[index] == Num.ZERO)
                return if (upward[index] == Num.ZERO) MIN_VALUE else MAX_VALUE
            val rs = upward[index] / downward[index]
            return MAX_VALUE - MAX_VALUE / (Num.ONE + rs)
        }
        return NaN
    }
}

/**
 * The relative strength index of [source] for [n] bars back, to evaluate overbought or oversold conditions.
 *
 * Oscillates between `0` (oversold) and `100` (overbought).
 *
 * **See:** [TradingView](https://www.tradingview.com/pine-script-reference/#fun_rsi)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample xmas.indicators.RelativeStrengthIndexTest.withIntSeries
 * @see [rma]
 */
fun rsi(source: Series, n: Int = 14): Series = RelativeStrengthIndex(source, n)
