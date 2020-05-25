package xmas.indicators

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import xmas.math.na
import xmas.math.numOf
import xmas.series.seriesOf

internal class SimpleMovingAverageTest {

    @Test
    fun withIntSeries() {

        // create the series for numbers
        val series = seriesOf(1, 2, 3)

        // create a sma(2) series
        val sma = sma(series, 2)

        assertThat(sma[0]).isEqualTo(numOf(2.5))   // current value
        assertThat(sma[1]).isEqualTo(numOf(1.5))   // previous value
        assertThat(sma[2]).isEqualTo(na)           // oldest value
    }
}
