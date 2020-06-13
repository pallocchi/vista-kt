package vista.data

import java.net.URL

/**
 * Loads the market data from a CSV at given [url].
 *
 * The expected columns are date, open, high, low, close and volume.
 *
 * @param url URL where the CSV can be found.
 * @param reversed If bars should be reversed or not
 */
fun dataOf(url: String, reversed: Boolean = true): Data {
    val bars = mutableListOf<Data.Bar>()
    URL(url).readText().lines().drop(1).forEach {
        val split = it.split(",")
        val bar = Data.Bar(split[0], split[1], split[2], split[3], split[4], split[5])
        bars.add(bar)
    }
    return Data(if (reversed) bars.asReversed() else bars)
}