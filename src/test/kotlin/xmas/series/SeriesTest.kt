package xmas.series

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class SeriesTest {

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
