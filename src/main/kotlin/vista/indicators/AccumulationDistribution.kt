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
import vista.series.Series

/**
 * Accumulation/Distribution (ADL) indicator.
 *
 * Note as this indicator is an accumulation the calculated values depends on the start date,
 * so in order to compare the results with others, the difference between periods should be used
 * instead of the absolute value.
 */
internal class AccumulationDistribution(
    close: Series,
    high: Series,
    low: Series,
    volume: Series
) : CachedIndicator(close) {

    override val size: Int get() = mfv.size

    private val mfv: Series

    init {
        //TODO: What if high == low???
        val mfm = ((close - low) - (high - close)) / (high - low)
        mfv = mfm * volume
    }

    override fun calculate(i: Int): Num {
        if (i == size - 1)
            return mfv[i]
        return mfv[i] + this[i + 1]
    }
}

/**
 * The money flow multiplier, used by Chaikin indicators.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volume?id=accumulationdistribution-adl)
 *
 * @param close Series of close prices
 * @param high Series of high prices
 * @param low Series of low prices
 * @sample vista.indicators.MovingAverageConvergenceDivergenceTest.withIntSeries
 */
fun accdist(close: Series, high: Series, low: Series, volume: Series): Series =
    AccumulationDistribution(close, high, low, volume)

/**
 * The money flow multiplier, used by Chaikin indicators.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volume?id=accumulationdistribution-adl)
 *
 * @sample vista.indicators.MovingAverageConvergenceDivergenceTest.withMarketData
 */
fun Data.accdist(): Series = accdist(close, high, low, volume)