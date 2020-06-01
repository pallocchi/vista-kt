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

package insight.indicators

import insight.data.close
import insight.loadAmazonData
import insight.loadIndicatorData
import insight.math.na
import insight.math.numOf
import insight.series.seriesOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MovingAverageConvergenceDivergenceTest {

    @Test
    fun withIntSeries() {
        val values = IntArray(50) { it }

        val series = seriesOf(*values)

        val (macd, signal, hist) = macd(series)

        assertThat(macd[0].round(2)).isEqualTo(numOf(7))    // current value
        assertThat(macd[1].round(2)).isEqualTo(numOf(7))    // previous value
        assertThat(macd[49]).isEqualTo(na)                  // oldest value

        assertThat(signal[0].round(2)).isEqualTo(numOf(7))  // current value
        assertThat(signal[1].round(2)).isEqualTo(numOf(7))  // previous value
        assertThat(signal[49]).isEqualTo(na)                // oldest value

        assertThat(hist[0].round(2)).isEqualTo(numOf(0))    // current value
        assertThat(hist[1].round(2)).isEqualTo(numOf(0))    // previous value
        assertThat(hist[49]).isEqualTo(na)                  // oldest value
    }

    @Test
    fun withMarketData() {
        val data = loadAmazonData()
        val expected = loadIndicatorData("macd.csv")
        val close = close(data)

        val (macd, signal, hist) = macd(close)

        for (i in 0..99) {
            assertThat(macd[i].round(2)).isEqualTo(expected[i][0])
            assertThat(signal[i].round(2)).isEqualTo(expected[i][1])
            assertThat(hist[i].round(2)).isEqualTo(expected[i][2])
        }
    }
}