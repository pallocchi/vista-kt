package vista.indicators

import vista.math.min
import vista.series.Series

/**
 * The ultimate oscillator, which measures momentum across three varying time frames.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/momentum?id=ultimate-oscillator-uo)
 *
 * @param close Series of close values
 * @param high Series of high values
 * @param low Series of low values
 * @param n1 Number of bars (length) for first time frame
 * @param n2 Number of bars (length) for second time frame
 * @param n3 Number of bars (length) for third time frame
 * @sample vista.indicators.UltimateOscillatorTest.withIntSeries
 */
fun uo(
    close: Series,
    high: Series,
    low: Series,
    n1: Int = 7,
    n2: Int = 14,
    n3: Int = 28
): Series {
    val bp = close - min(low, close(1))
    val tr = tr(close, high, low)
    val avg1 = sum(bp, n1) / sum(tr, n1)
    val avg2 = sum(bp, n2) / sum(tr, n2)
    val avg3 = sum(bp, n3) / sum(tr, n3)
    return (((avg1 * 4) + (avg2 * 2) + avg3) / 7) * 100
}