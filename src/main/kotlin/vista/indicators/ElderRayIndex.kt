package vista.indicators

import vista.data.Data
import vista.series.Series

/**
 * The elder-ray index, which help traders determine the trend direction and isolate spots to enter and exit trades.
 *
 * Returns a triple of bull-bear power, bull power and bear power.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/trend?id=elder-ray-index)
 *
 * @param close Series of close values
 * @param high Series of high values
 * @param low Series of low values
 * @param n Number of bars (length)
 * @sample vista.indicators.ElderRayIndexTest.withIntSeries
 */
fun eri(
    close: Series,
    high: Series,
    low: Series,
    n: Int = 13
): Triple<Series, Series, Series> {
    val ema = ema(close, n)
    val bullPower = high - ema
    val bearPower = low - ema
    val bullBearPower = bullPower + bearPower
    return Triple(bullBearPower, bullPower, bearPower)
}

/**
 * The elder-ray index, which help traders determine the trend direction and isolate spots to enter and exit trades.
 *
 * Returns a triple of bull-bear power, bull power and bear power.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/trend?id=elder-ray-index)
 *
 * @param data Market data
 * @param n Number of bars (length)
 * @sample vista.indicators.ElderRayIndexTest.withIntSeries
 */
fun eri(data: Data, n: Int = 13) = eri(data.close, data.high, data.low, n)