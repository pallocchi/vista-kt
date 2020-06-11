package vista.indicators

import vista.series.Series

/**
 * The awesome oscillator, used to measure market momentum.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/momentum?id=awesome-oscillator-ao)
 *
 * @param high Series of high values
 * @param low Series of low values
 * @sample vista.indicators.AwesomeOscillatorTest.withIntSeries
 */
fun ao(high: Series, low: Series): Series {
    val midpoints = (high + low) / 2
    return sma(midpoints, 5) - sma(midpoints, 34)
}