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

        val bars = listOf(
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
}