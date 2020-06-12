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

internal class CommodityChannelIndexTest {

    @Test
    fun withIntSeries() {
        val close = seriesOf(1..25)

        val cci = cci(close, 20)

        assertThat(cci[0].round(2)).isEqualTo(numOf(126.67))    // current value
        assertThat(cci[1].round(2)).isEqualTo(numOf(126.67))    // previous value
        assertThat(cci[5].round(2)).isEqualTo(numOf(126.67))
        assertThat(cci[6]).isEqualTo(na)
    }

    @Test
    fun withMarketData() {
        val data = loadAmazonData()
        val expected = loadIndicatorData("cci.csv")

        val close = data.close

        val cci = cci(close)

        for (i in 0..99)
            assertThat(cci[i].round(2)).isEqualTo(expected[i][0])
    }
}