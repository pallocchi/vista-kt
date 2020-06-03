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
import vista.math.min
import vista.series.Series

/**
 * Lowest indicator.
 */
internal class Lowest(
    private val source: Series,
    private val n: Int
) : CachedIndicator(source) {

    override val size: Int get() = source.size + 1 - n

    override fun calculate(i: Int): Num {
        var lowest = source[i]
        for (j in 1 until n)
            lowest = min(lowest, source[i + j])
        return lowest
    }
}

/**
 * The lowest value for [n] bars back.
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.LowestTest.lowest
 */
fun lowest(source: Series, n: Int): Series = Lowest(source, n)