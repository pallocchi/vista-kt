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

package xmas.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import xmas.math.numOf

internal class DataTest {

    private var data: Data = Data()

    @BeforeEach
    fun init() {

        // create the market data

        val bars = mutableListOf(
            Data.Bar(
                date = "12345678",
                open = 2361.01,
                high = 2391.37,
                low = 2353.21,
                close = 2388.85,
                volume = 3648128.0
            ),
            Data.Bar(
                date = "12345679",
                open = 2368.52,
                high = 2411.0,
                low = 2356.37,
                close = 2409.78,
                volume = 4234951.0
            )
        )

        data = Data(bars)
    }

    @Test
    fun withIndexingOperator() {
        assertThat(data[0]?.date).isEqualTo("12345679") // current bar
        assertThat(data[1]?.date).isEqualTo("12345678") // previous bar
    }

    @Test
    fun withOpenSeries() {

        // create the open price series

        val open = open(data)

        assertThat(open[0]).isEqualTo(numOf(2368.52))  // current value
        assertThat(open[1]).isEqualTo(numOf(2361.01))  // previous value
    }

    @Test
    fun withHighSeries() {

        // create the high price series

        val high = high(data)

        assertThat(high[0]).isEqualTo(numOf(2411.0))   // current value
        assertThat(high[1]).isEqualTo(numOf(2391.37))  // previous value
    }

    @Test
    fun withLowSeries() {

        // create the low price series

        val low = low(data)

        assertThat(low[0]).isEqualTo(numOf(2356.37))  // current value
        assertThat(low[1]).isEqualTo(numOf(2353.21))  // previous value
    }

    @Test
    fun withCloseSeries() {

        // create the close price series

        val close = close(data)

        assertThat(close[0]).isEqualTo(numOf(2409.78))  // current value
        assertThat(close[1]).isEqualTo(numOf(2388.85))  // previous value
    }

    @Test
    fun withVolumeSeries() {

        // create the volume series

        val volume = volume(data)

        assertThat(volume[0]).isEqualTo(numOf(4234951)) // current value
        assertThat(volume[1]).isEqualTo(numOf(3648128)) // previous value
    }

    @Test
    fun withNewBar() {

        // create the close price series

        val close = close(data)

        assertThat(close[0]).isEqualTo(numOf(2409.78))  // current value
        assertThat(close[1]).isEqualTo(numOf(2388.85))  // previous value

        data.add(
            Data.Bar(
                date = "12345680",
                open = 2366.80,
                high = 2407.7,
                low = 2337.81,
                close = 2367.92,
                volume = 4782919.0
            )
        )

        assertThat(close[0]).isEqualTo(numOf(2367.92))  // current value
        assertThat(close[1]).isEqualTo(numOf(2409.78))  // previous value
        assertThat(close[2]).isEqualTo(numOf(2388.85))  // oldest value
    }
}