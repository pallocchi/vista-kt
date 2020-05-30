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

package xmas.indicators

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import xmas.data.close
import xmas.loadAmazonData
import xmas.loadIndicatorData
import xmas.math.na
import xmas.math.numOf
import xmas.series.seriesOf

internal class SimpleMovingAverageTest {

    @Test
    fun withIntSeries() {

        val series = seriesOf(1, 2, 3)

        val sma = sma(series, 2)

        assertThat(sma[0]).isEqualTo(numOf(2.5))   // current value
        assertThat(sma[1]).isEqualTo(numOf(1.5))   // previous value
        assertThat(sma[2]).isEqualTo(na)           // oldest value
    }

    @Test
    fun withMarketData() {

        val data = loadAmazonData()
        val expected = loadIndicatorData("sma.csv")
        val close = close(data)

        val actual = sma(close, 5)

        for (i in 0 until data.size)
            assertThat(actual[i].round(2)).isEqualTo(expected[i])
    }
}
