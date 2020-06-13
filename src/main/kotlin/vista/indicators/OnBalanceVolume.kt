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
 * On-Balance volume (OBV) indicator.
 *
 * Note as this indicator is an accumulation the calculated values depends on the start date,
 * so in order to compare the results with others, the difference between periods should be used
 * instead of the absolute value.
 */
internal class OnBalanceVolume(
    private val close: Series,
    private val volume: Series
) : CachedIndicator(close) {

    override val size: Int get() = close.size - 1

    override fun calculate(i: Int): Num {
        if (i == size - 1)
            return calculateCurrentOBV(i)
        return this[i + 1] + calculateCurrentOBV(i)
    }

    private fun calculateCurrentOBV(i: Int): Num {
        return when {
            close[i] > close[i + 1] -> volume[i]
            close[i] < close[i + 1] -> -volume[i]
            else -> Num.ZERO
        }
    }
}

/**
 * The on-balance volume indicator, which uses volume flow to predict changes in price.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volume?id=on-balance-volume-obv)
 *
 * @param close Series of close prices
 * @param volume Series of volume values
 * @sample vista.indicators.OnBalanceVolumeTest.withIntSeries
 */
fun obv(close: Series, volume: Series): Series = OnBalanceVolume(close, volume)

/**
 * The on-balance volume indicator, which uses volume flow to predict changes in price.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/volume?id=on-balance-volume-obv)
 *
 * @sample vista.indicators.OnBalanceVolumeTest.withMarketData
 */
fun Data.obv(): Series = obv(close, volume)