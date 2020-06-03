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
import vista.data.close
import vista.data.high
import vista.data.low
import vista.loadAmazonData
import vista.loadIndicatorData
import vista.math.na
import vista.math.numOf
import vista.series.seriesOf

internal class StochasticOscillatorTest {

    @Test
    fun withIntSeries() {
        val close = seriesOf(*IntArray(50) { it })

        val low = seriesOf(*IntArray(50) { it - it % 2 })
        val high = seriesOf(*IntArray(50) { it + it % 2 })

        val (k, d) = stoch(close, high, low)

        assertThat(k[0].round(2)).isEqualTo(numOf(95.24))    // current value
        assertThat(k[1].round(2)).isEqualTo(numOf(97.62))    // previous value
        assertThat(k[34].round(2)).isEqualTo(numOf(95.24))
        assertThat(k[35]).isEqualTo(na)

        assertThat(d[0].round(2)).isEqualTo(numOf(96.03))    // current value
        assertThat(d[1].round(2)).isEqualTo(numOf(96.83))    // previous value
        assertThat(d[32].round(2)).isEqualTo(numOf(96.03))
        assertThat(d[33]).isEqualTo(na)
    }

    @Test
    fun withMarketData() {
        val data = loadAmazonData()
        val expected = loadIndicatorData("stoch.csv")

        val close = close(data)

        val low = low(data)
        val high = high(data)

        val (k, d) = stoch(close, high, low)

        for (i in 0..99) {
            assertThat(k[i].round(2)).isEqualTo(expected[i][0])
            assertThat(d[i].round(2)).isEqualTo(expected[i][1])
        }
    }
}