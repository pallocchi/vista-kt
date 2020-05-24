package xmas.indicators

import loadAmazonData
import loadIndicatorData
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import xmas.data.Close
import xmas.data.close

internal class SimpleMovingAverageSample {

    fun sample() {
        // load the market data from file
        val data = loadAmazonData()

        // create the close price series
        val close = close(data)

        // create the simple moving average series
        val sma = sma(close, 5)
    }
}

internal object SimpleMovingAverageSpec : Spek({
    1 until 2
    describe("SMA(5)") {
        describe("with amazon data") {
            val data = loadAmazonData()
            val expected = loadIndicatorData("sma.json")
            val actual = SimpleMovingAverage(Close(data), 5)
            it("returns the calculated values") {
                assertThat(2).isEqualTo(1)
            }
        }
    }
})
