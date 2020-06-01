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

package xmas.series

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import xmas.math.numOf

internal class SeriesTest {

    @Test
    fun withIndexingOperator() {
        val series = seriesOf(10, 20, 30)

        assertThat(series[0]).isEqualTo(numOf(30)) // current bar
        assertThat(series[1]).isEqualTo(numOf(20)) // previous bar
        assertThat(series[2]).isEqualTo(numOf(10)) // oldest bar
    }

    @Test
    fun plus() {
        val a = seriesOf(1, 2, 3)
        val b = seriesOf(1, 2, 3)

        // adding another series

        val c = a + b

        assertThat(c[0]).isEqualTo(numOf(6))
        assertThat(c[1]).isEqualTo(numOf(4))
        assertThat(c[2]).isEqualTo(numOf(2))

        // adding a fixed int

        val d = a + 1

        assertThat(d[0]).isEqualTo(numOf(4))
        assertThat(d[1]).isEqualTo(numOf(3))
        assertThat(d[2]).isEqualTo(numOf(2))

        // adding a fixed double

        val e = a + .5

        assertThat(e[0]).isEqualTo(numOf(3.5))
        assertThat(e[1]).isEqualTo(numOf(2.5))
        assertThat(e[2]).isEqualTo(numOf(1.5))
    }

    @Test
    fun minus() {
        val a = seriesOf(1, 2, 3)
        val b = seriesOf(6, 5, 4)


        // subtracting another series

        val c = b - a

        assertThat(c[0]).isEqualTo(numOf(1))
        assertThat(c[1]).isEqualTo(numOf(3))
        assertThat(c[2]).isEqualTo(numOf(5))

        // subtracting a fixed int

        val d = a - 1

        assertThat(d[0]).isEqualTo(numOf(2))
        assertThat(d[1]).isEqualTo(numOf(1))
        assertThat(d[2]).isEqualTo(numOf(0))

        // subtracting a fixed double

        val e = a - .5

        assertThat(e[0]).isEqualTo(numOf(2.5))
        assertThat(e[1]).isEqualTo(numOf(1.5))
        assertThat(e[2]).isEqualTo(numOf(.5))
    }

    @Test
    fun times() {
        val a = seriesOf(1, 2, 3)
        val b = seriesOf(1, 2, 3)

        // multiplying by another series

        val c = a * b

        assertThat(c[0]).isEqualTo(numOf(9))
        assertThat(c[1]).isEqualTo(numOf(4))
        assertThat(c[2]).isEqualTo(numOf(1))

        // multiplying by a fixed int

        val d = a * 2

        assertThat(d[0]).isEqualTo(numOf(6))
        assertThat(d[1]).isEqualTo(numOf(4))
        assertThat(d[2]).isEqualTo(numOf(2))

        // multiplying by a fixed double

        val e = a * .5

        assertThat(e[0]).isEqualTo(numOf(1.5))
        assertThat(e[1]).isEqualTo(numOf(1))
        assertThat(e[2]).isEqualTo(numOf(.5))
    }

    @Test
    fun div() {
        val a = seriesOf(1, 2, 3)
        val b = seriesOf(4, 5, 6)

        // dividing by another series

        val c = b / a

        assertThat(c[0]).isEqualTo(numOf(2))
        assertThat(c[1]).isEqualTo(numOf(2.5))
        assertThat(c[2]).isEqualTo(numOf(4))

        // dividing by a fixed int

        val d = a / 2

        assertThat(d[0]).isEqualTo(numOf(1.5))
        assertThat(d[1]).isEqualTo(numOf(1))
        assertThat(d[2]).isEqualTo(numOf(.5))

        // dividing by a fixed double

        val e = a / .5

        assertThat(e[0]).isEqualTo(numOf(6))
        assertThat(e[1]).isEqualTo(numOf(4))
        assertThat(e[2]).isEqualTo(numOf(2))
    }

    @Test
    fun compareTo() {
        val a = seriesOf(1, 2)
        val b = seriesOf(2, 1)

        assertThat(a > b).isTrue()
        assertThat(a < b).isFalse()
    }

    @Test
    fun cross() {
        val bearish = seriesOf(1, 0)
        val bullish = seriesOf(0, 1)

        assertThat(bearish cross bullish).isTrue()
        assertThat(bullish cross bearish).isTrue()
        assertThat(bullish cross bullish).isFalse()
    }

    @Test
    fun crossOver() {
        val bearish = seriesOf(1, 0)
        val bullish = seriesOf(0, 1)

        assertThat(bearish crossOver bullish).isFalse()
        assertThat(bullish crossOver bearish).isTrue()
        assertThat(bullish crossOver bullish).isFalse()
    }

    @Test
    fun crossUnder() {
        val bearish = seriesOf(1, 0)
        val bullish = seriesOf(0, 1)

        assertThat(bearish crossUnder bullish).isTrue()
        assertThat(bullish crossUnder bearish).isFalse()
        assertThat(bullish crossUnder bullish).isFalse()
    }
}
