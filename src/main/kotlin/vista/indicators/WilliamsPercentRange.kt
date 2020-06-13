package vista.indicators

import vista.data.Data
import vista.series.Series

/**
 * The Williams percent range (%R), which measures overbought and oversold levels.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/momentum?id=williams-r)
 *
 * @param close Series of close values
 * @param high Series of high values
 * @param low Series of low values
 * @param n Number of bars (length)
 * @sample vista.indicators.WilliamsPercentRangeTest.withIntSeries
 */
fun wpr(close: Series, high: Series, low: Series, n: Int = 14): Series {
    val highest = highest(high, n)
    val lowest = lowest(low, n)
    return (highest - close) / (highest - lowest) * -100
}

/**
 * The Williams percent range (%R), which measures overbought and oversold levels.
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/momentum?id=williams-r)
 *
 * @param n Number of bars (length)
 * @sample vista.indicators.WilliamsPercentRangeTest.withMarketData
 */
fun Data.wpr(n: Int = 14) = wpr(close, high, low, n)