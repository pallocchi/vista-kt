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

package vista.rules

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import vista.series.seriesOf

internal class RulesTest {

    @Test
    fun cross() {

        val bearish = seriesOf(1, 0)
        val bullish = seriesOf(0, 1)

        assertThat(cross(bearish, bullish)).isTrue()
        assertThat(cross(bullish, bearish)).isTrue()
        assertThat(cross(bullish, bullish)).isFalse()
    }

    @Test
    fun crossover() {

        val bearish = seriesOf(1, 0)
        val bullish = seriesOf(0, 1)

        assertThat(crossover(bearish, bullish)).isFalse()
        assertThat(crossover(bullish, bearish)).isTrue()
        assertThat(crossover(bullish, bullish)).isFalse()
    }

    @Test
    fun crossunder() {

        val bearish = seriesOf(1, 0)
        val bullish = seriesOf(0, 1)

        assertThat(crossunder(bearish, bullish)).isTrue()
        assertThat(crossunder(bullish, bearish)).isFalse()
        assertThat(crossunder(bullish, bullish)).isFalse()
    }
}
