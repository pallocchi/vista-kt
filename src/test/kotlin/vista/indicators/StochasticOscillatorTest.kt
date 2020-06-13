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
import vista.loadAmazonData
import vista.loadIndicatorData
import vista.math.na
import vista.math.numOf
import vista.series.seriesOf

internal class StochasticOscillatorTest {

    @Test
    fun withIntSeries() {
        val close = seriesOf(1..20)

        val high = close * 1.5
        val low = close * 0.5

        val (k, d) = stoch(close, high, low)

        assertThat(k[0].round(2)).isEqualTo(numOf(62.76))    // current value
        assertThat(k[1].round(2)).isEqualTo(numOf(63.28))    // previous value
        assertThat(k[4].round(2)).isEqualTo(numOf(65.14))
        assertThat(k[5]).isEqualTo(na)

        assertThat(d[0].round(2)).isEqualTo(numOf(63.29))    // current value
        assertThat(d[1].round(2)).isEqualTo(numOf(63.86))    // previous value
        assertThat(d[2].round(2)).isEqualTo(numOf(64.48))
        assertThat(d[3]).isEqualTo(na)
    }

    @Test
    fun withMarketData() {
        val data = loadAmazonData()
        val expected = loadIndicatorData("stoch.csv")

        val (k, d) = data.stoch()

        for (i in 0..99) {
            assertThat(k[i].round(2)).isEqualTo(expected[i][0])
            assertThat(d[i].round(2)).isEqualTo(expected[i][1])
        }
    }
}