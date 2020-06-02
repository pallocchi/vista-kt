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

package insight.math

import insight.series.seriesOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FunctionsTest {

    @Test
    fun minWithInt() {
        assertThat(min(1, 1)).isEqualTo(1)
        assertThat(min(1, 2)).isEqualTo(1)
        assertThat(min(2, 1)).isEqualTo(1)
    }

    @Test
    fun maxWithInt() {
        assertThat(max(1, 1)).isEqualTo(1)
        assertThat(max(1, 2)).isEqualTo(2)
        assertThat(max(2, 1)).isEqualTo(2)
    }

    @Test
    fun minWithNum() {
        assertThat(min(numOf(1), numOf(1))).isEqualTo(numOf(1))
        assertThat(min(numOf(1), numOf(2))).isEqualTo(numOf(1))
        assertThat(min(numOf(2), numOf(1))).isEqualTo(numOf(1))
    }

    @Test
    fun maxWithNum() {
        assertThat(max(numOf(1), numOf(1))).isEqualTo(numOf(1))
        assertThat(max(numOf(1), numOf(2))).isEqualTo(numOf(2))
        assertThat(max(numOf(2), numOf(1))).isEqualTo(numOf(2))
    }

    @Test
    fun minWithSeries() {
        val a = seriesOf(1, 2, 3)
        val b = seriesOf(3, 2, 1)

        // min between two series

        val c = min(a, b)

        assertThat(c[0]).isEqualTo(numOf(1))
        assertThat(c[1]).isEqualTo(numOf(2))
        assertThat(c[2]).isEqualTo(numOf(1))

        // min between series and number

        val d = min(a, numOf(2))

        assertThat(d[0]).isEqualTo(numOf(2))
        assertThat(d[1]).isEqualTo(numOf(2))
        assertThat(d[2]).isEqualTo(numOf(1))
    }

    @Test
    fun maxWithSeries() {
        val a = seriesOf(1, 2, 3)
        val b = seriesOf(3, 2, 1)

        // max between two series

        val c = max(a, b)

        assertThat(c[0]).isEqualTo(numOf(3))
        assertThat(c[1]).isEqualTo(numOf(2))
        assertThat(c[2]).isEqualTo(numOf(3))

        // max between series and number

        val d = max(a, numOf(2))

        assertThat(d[0]).isEqualTo(numOf(3))
        assertThat(d[1]).isEqualTo(numOf(2))
        assertThat(d[2]).isEqualTo(numOf(2))
    }

    @Test
    fun sqrtWithSeries() {
        val a = seriesOf(1, 4, 9)

        val b = sqrt(a)

        assertThat(b[0]).isEqualTo(numOf(3))
        assertThat(b[1]).isEqualTo(numOf(2))
        assertThat(b[2]).isEqualTo(numOf(1))
    }

    @Test
    fun powWithSeries() {
        val a = seriesOf(1, 2, 3)

        val b = pow(a, 2)

        assertThat(b[0]).isEqualTo(numOf(9))
        assertThat(b[1]).isEqualTo(numOf(4))
        assertThat(b[2]).isEqualTo(numOf(1))
    }

    @Test
    fun absWithSeries() {
        val a = seriesOf(1, -2, 3)

        val b = abs(a)

        assertThat(b[0]).isEqualTo(numOf(3))
        assertThat(b[1]).isEqualTo(numOf(2))
        assertThat(b[2]).isEqualTo(numOf(1))
    }
}