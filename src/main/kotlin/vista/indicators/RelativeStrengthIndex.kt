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
import vista.math.max
import vista.math.min
import vista.series.Series

/**
 * Relative strength index (RSI) indicator.
 */
internal class RelativeStrengthIndex(
    private val source: Series,
    private val n: Int
) : CachedIndicator(source) {

    companion object {
        val MIN_VALUE = Num.ZERO
        val MAX_VALUE = Num.HUNDRED
    }

    override val size: Int get() = min(upward.size, downward.size)

    private val upward = rma(max(source - source(1), Num.ZERO), n)
    private val downward = rma(max(source(1) - source, Num.ZERO), n)

    override fun calculate(i: Int): Num {
        if (downward[i] == Num.ZERO)
            return if (upward[i] == Num.ZERO) MIN_VALUE else MAX_VALUE
        val rs = upward[i] / downward[i]
        return MAX_VALUE - MAX_VALUE / (Num.ONE + rs)
    }
}

/**
 * The relative strength index of [source] for [n] bars back, to evaluate overbought or oversold conditions.
 *
 * Oscillates between `0` (oversold) and `100` (overbought).
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/momentum?id=relative-strength-index-rsi)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.RelativeStrengthIndexTest.withIntSeries
 * @see [rma]
 */
fun rsi(source: Series, n: Int = 14): Series = RelativeStrengthIndex(source, n)
