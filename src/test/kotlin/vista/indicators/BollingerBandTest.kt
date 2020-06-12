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

internal class BollingerBandTest {

    @Test
    fun withIntSeries() {
        val series = seriesOf(1..50)

        val (middle, upper, lower) = bb(series)

        assertThat(middle[0].round(2)).isEqualTo(numOf(40.50))    // current value
        assertThat(middle[1].round(2)).isEqualTo(numOf(39.50))    // previous value
        assertThat(middle[30].round(2)).isEqualTo(numOf(10.50))
        assertThat(middle[31]).isEqualTo(na)

        assertThat(upper[0].round(2)).isEqualTo(numOf(52.03))     // current value
        assertThat(upper[1].round(2)).isEqualTo(numOf(51.03))     // previous value
        assertThat(upper[30].round(2)).isEqualTo(numOf(22.03))
        assertThat(upper[31]).isEqualTo(na)

        assertThat(lower[0].round(2)).isEqualTo(numOf(28.97))     // current value
        assertThat(lower[1].round(2)).isEqualTo(numOf(27.97))     // previous value
        assertThat(lower[30].round(2)).isEqualTo(numOf(-1.03))
        assertThat(lower[31]).isEqualTo(na)
    }

    @Test
    fun withMarketData() {
        val data = loadAmazonData()
        val expected = loadIndicatorData("bb.csv")
        val close = data.close

        val (middle, upper, lower) = bb(close)

        for (i in 0..99) {
            assertThat(middle[i].round(2)).isEqualTo(expected[i][0])
            assertThat(upper[i].round(2)).isEqualTo(expected[i][1])
            assertThat(lower[i].round(2)).isEqualTo(expected[i][2])
        }
    }
}