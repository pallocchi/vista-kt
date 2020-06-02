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

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NumbersTest {

    @Test
    fun unaryMinus() {
        assertThat(-numOf(1)).isEqualTo(numOf(-1))
        assertThat(-numOf(-1)).isEqualTo(numOf(1))
        assertThat(NaN).isEqualTo(NaN)
    }

    @Test
    fun plus() {
        val one = numOf(1)

        assertThat(one + one).isEqualTo(numOf(2))
        assertThat(one + NaN).isEqualTo(NaN)
        assertThat(NaN + one).isEqualTo(NaN)
    }

    @Test
    fun minus() {
        val one = numOf(1)

        assertThat(one - one).isEqualTo(numOf(0))
        assertThat(one - NaN).isEqualTo(NaN)
        assertThat(NaN - one).isEqualTo(NaN)
    }

    @Test
    fun times() {
        val one = numOf(1)

        assertThat(one * one).isEqualTo(numOf(1))
        assertThat(one * NaN).isEqualTo(NaN)
        assertThat(NaN * one).isEqualTo(NaN)
    }

    @Test
    fun div() {
        val one = numOf(1)

        assertThat(one / one).isEqualTo(numOf(1))
        assertThat(one / NaN).isEqualTo(NaN)
        assertThat(NaN / one).isEqualTo(NaN)
    }

    @Test
    fun compare() {
        val one = numOf(1)
        val two = numOf(2)

        assertThat(one.compareTo(two)).isNegative()
        assertThat(two.compareTo(one)).isPositive()
        assertThat(one.compareTo(NaN)).isZero()
        assertThat(NaN.compareTo(one)).isZero()
    }

    @Test
    fun round() {
        assertThat(numOf(1.01).round(1, RoundMode.UP)).isEqualTo(numOf(1.1))
        assertThat(numOf(1.01).round(1, RoundMode.DOWN)).isEqualTo(numOf(1.0))
        assertThat(numOf(1.01).round(1, RoundMode.HALF_UP)).isEqualTo(numOf(1.0))
        assertThat(numOf(1.05).round(1, RoundMode.HALF_UP)).isEqualTo(numOf(1.1))
        assertThat(numOf(1.01).round(1, RoundMode.HALF_DOWN)).isEqualTo(numOf(1.0))
        assertThat(numOf(1.05).round(1, RoundMode.HALF_DOWN)).isEqualTo(numOf(1.0))
    }

    @Test
    fun matches() {
        assertThat(numOf(1.0).matches(numOf(1.01), .01)).isTrue()
        assertThat(numOf(1.0).matches(numOf(1.01), .0099)).isFalse()
    }

    @Test
    fun abs() {
        assertThat(numOf(1).abs()).isEqualTo(numOf(1))
        assertThat(numOf(-1).abs()).isEqualTo(numOf(1))
        assertThat(NaN.abs()).isEqualTo(NaN)
    }

    @Test
    fun sqrt() {
        assertThat(numOf(1).sqrt()).isEqualTo(numOf(1))
        assertThat(numOf(4).sqrt()).isEqualTo(numOf(2))
        assertThat(numOf(9).sqrt()).isEqualTo(numOf(3))
        assertThat(NaN.sqrt()).isEqualTo(NaN)
    }

    @Test
    fun pow() {
        assertThat(numOf(2).pow(0)).isEqualTo(numOf(1))
        assertThat(numOf(2).pow(1)).isEqualTo(numOf(2))
        assertThat(numOf(2).pow(2)).isEqualTo(numOf(4))
        assertThat(numOf(3).pow(2)).isEqualTo(numOf(9))
        assertThat(NaN.pow(2)).isEqualTo(NaN)
    }

    @Test
    fun isEqual() {
        val one = numOf(1)
        val two = numOf(2)

        assertThat(one.isEqual(one)).isTrue()
        assertThat(one.isEqual(two)).isFalse()
        assertThat(one.isEqual(NaN)).isFalse()
        assertThat(NaN.isEqual(one)).isFalse()
        assertThat(NaN.isEqual(NaN)).isTrue()
    }

    @Test
    fun toInt() {
        assertThat(numOf(1).toInt()).isEqualTo(1)
        assertThat(numOf(1L).toInt()).isEqualTo(1)
        assertThat(numOf(1.0).toInt()).isEqualTo(1)
    }

    @Test
    fun toLong() {
        assertThat(numOf(1).toLong()).isEqualTo(1)
        assertThat(numOf(1L).toLong()).isEqualTo(1)
        assertThat(numOf(1.0).toLong()).isEqualTo(1)
    }

    @Test
    fun toDouble() {
        assertThat(numOf(1).toDouble()).isEqualTo(1.0)
        assertThat(numOf(1L).toDouble()).isEqualTo(1.0)
        assertThat(numOf(1.0).toDouble()).isEqualTo(1.0)
    }

    @Test
    fun toFloat() {
        assertThat(numOf(1).toFloat()).isEqualTo(1.0F)
        assertThat(numOf(1L).toFloat()).isEqualTo(1.0F)
        assertThat(numOf(1.0).toFloat()).isEqualTo(1.0F)
    }

    @Test
    fun na() {
        assertThat(na).isEqualTo(NaN)
    }
}
