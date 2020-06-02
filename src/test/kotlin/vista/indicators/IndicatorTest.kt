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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import vista.data.Data
import vista.data.close
import vista.math.Num
import vista.math.numOf
import vista.series.Series
import vista.series.seriesOf

internal class IndicatorTest {

    @Test
    fun size() {

        val series = seriesOf(1, 2, 3)
        val indicator = FoolIndicator(series)

        assertThat(series.size).isEqualTo(3)
        assertThat(indicator.size).isEqualTo(3)
    }

    @Test
    fun withNewValue() {

        // create the market data

        val bars = mutableListOf(
            Data.Bar(
                date = "12345678",
                open = 2361.01,
                high = 2391.37,
                low = 2353.21,
                close = 2388.85,
                volume = 3648128.0
            )
        )

        val data = Data(bars)

        // create the close price series
        val close = close(data)

        // create the indicator
        val indicator = FoolIndicator(close)

        assertThat(indicator[0]).isEqualTo(numOf(2388.85))

        data.add(
            Data.Bar(
                date = "12345679",
                open = 2368.52,
                high = 2411.0,
                low = 2356.37,
                close = 2409.78,
                volume = 4234951.0
            )
        )

        assertThat(indicator[0]).isEqualTo(numOf(2409.78))
        assertThat(indicator[1]).isEqualTo(numOf(2388.85))
    }

    /**
     * Indicator with no calculation.
     */
    private class FoolIndicator(
        private val source: Series
    ) : Indicator(source) {

        override val size: Int get() = source.size

        /**
         * Returns the [index] value of [source].
         */
        override fun calculate(index: Int): Num = source[index]
    }
}
