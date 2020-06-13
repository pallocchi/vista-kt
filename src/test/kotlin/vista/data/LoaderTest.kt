package vista.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import vista.math.numOf


internal class LoaderTest {

    @Test
    fun withLocalURL() {
        val url = javaClass.classLoader.getResource("amzn.csv")
        val data = dataOf(url!!.toString())

        assertThat(data.open[0]).isEqualTo(numOf(2415.94))
        assertThat(data.high[0]).isEqualTo(numOf(2442.37))
        assertThat(data.low[0]).isEqualTo(numOf(2398.20))
        assertThat(data.close[0]).isEqualTo(numOf(2442.37))
        assertThat(data.volume[0]).isEqualTo(numOf(3529329))
    }
}