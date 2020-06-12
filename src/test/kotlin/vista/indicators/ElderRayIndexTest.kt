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

internal class ElderRayIndexTest {

    @Test
    fun withIntSeries() {
        val close = seriesOf(1..20)

        val high = close * 1.5
        val low = close * 0.5

        val (bb, bull, bear) = eri(close, high, low, 13)

        assertThat(bb[0].round(2)).isEqualTo(numOf(12))        // current value
        assertThat(bb[1].round(2)).isEqualTo(numOf(12))        // previous value
        assertThat(bb[7].round(2)).isEqualTo(numOf(12))
        assertThat(bb[8]).isEqualTo(na)

        assertThat(bull[0].round(2)).isEqualTo(numOf(16.0))    // current value
        assertThat(bull[1].round(2)).isEqualTo(numOf(15.5))    // previous value
        assertThat(bull[7].round(2)).isEqualTo(numOf(12.5))
        assertThat(bull[8]).isEqualTo(na)

        assertThat(bear[0].round(2)).isEqualTo(numOf(-4.0))    // current value
        assertThat(bear[1].round(2)).isEqualTo(numOf(-3.5))    // previous value
        assertThat(bear[7].round(2)).isEqualTo(numOf(-0.5))
        assertThat(bear[8]).isEqualTo(na)
    }

    @Test
    fun withMarketData() {
        val data = loadAmazonData()
        val expected = loadIndicatorData("eri.csv")

        val (bb, bull, bear) = data.eri()

        for (i in 0..99) {
            assertThat(bb[i].round(2)).isEqualTo(expected[i][0])
            assertThat(bull[i].round(2)).isEqualTo(expected[i][1])
            assertThat(bear[i].round(2)).isEqualTo(expected[i][2])
        }
    }
}