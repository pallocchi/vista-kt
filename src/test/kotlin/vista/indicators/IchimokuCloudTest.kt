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
import vista.math.numOf
import vista.series.seriesOf

internal class IchimokuCloudTest {

    @Test
    fun withIntSeries() {
        val low = seriesOf(1..100)
        val high = seriesOf(2..101)

        val ichimoku = ichimoku(high, low, 9, 26, 52, 26)

        assertThat(ichimoku.ts[0]).isEqualTo(numOf(96.50))    // current value
        assertThat(ichimoku.ts[1]).isEqualTo(numOf(95.50))    // previous value

        assertThat(ichimoku.ks[0]).isEqualTo(numOf(88.0))     // current value
        assertThat(ichimoku.ks[1]).isEqualTo(numOf(87.0))     // previous value

        assertThat(ichimoku.ssa[0]).isEqualTo(numOf(67.25))   // current value
        assertThat(ichimoku.ssa[1]).isEqualTo(numOf(66.25))   // previous value

        assertThat(ichimoku.ssb[0]).isEqualTo(numOf(50.0))    // current value
        assertThat(ichimoku.ssb[1]).isEqualTo(numOf(49.0))    // previous value
    }

    @Test
    fun withMarketData() {
        val data = loadAmazonData()
        val expected = loadIndicatorData("ichimoku.csv")

        val high = data.high
        val low = data.low

        val actual = ichimoku(high, low)

        for (i in 0..99) {
            assertThat(actual.ts[i].round(2)).isEqualTo(expected[i][0])
            assertThat(actual.ks[i].round(2)).isEqualTo(expected[i][1])
            assertThat(actual.ssa[i].round(2)).isEqualTo(expected[i][2])
            assertThat(actual.ssb[i].round(2)).isEqualTo(expected[i][3])
        }
    }
}