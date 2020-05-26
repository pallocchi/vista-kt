package xmas.rules

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import xmas.series.seriesOf

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
