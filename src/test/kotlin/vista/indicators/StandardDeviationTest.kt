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

internal class StandardDeviationTest {

    @Test
    fun withIntSeries() {
        val series = seriesOf(1, 2, 4)

        val stdev = stdev(series, 2)

        assertThat(stdev[0]).isEqualTo(numOf(1))   // current value
        assertThat(stdev[1]).isEqualTo(numOf(.5))   // previous value
        assertThat(stdev[2]).isEqualTo(na)          // oldest value
    }

    @Test
    fun withMarketData() {
        val data = loadAmazonData()
        val expected = loadIndicatorData("stdev.csv")
        val close = data.close

        val actual = stdev(close, 20)

        for (i in 0..99)
            assertThat(actual[i].round(2)).isEqualTo(expected[i][0])
    }
}
