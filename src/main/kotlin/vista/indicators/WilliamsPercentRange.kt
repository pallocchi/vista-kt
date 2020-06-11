package vista.indicators

import vista.data.Data
import vista.data.close
import vista.data.high
import vista.data.low
import vista.series.Series

/**
 * The Williams percent range (%R), which measures overbought and oversold levels.
 *
 * **See:** [Investopedia](https://www.investopedia.com/terms/w/williamsr.asp)
 *
 * @param close Series of close values
 * @param high Series of high values
 * @param low Series of low values
 * @param n Number of bars (length)
 * @sample vista.indicators.WilliamsPercentRangeTest.withIntSeries
 */
fun williams(close: Series, high: Series, low: Series, n: Int = 14): Series {
    val highest = highest(high, n)
    val lowest = lowest(low, n)
    return (highest - close) / (highest - lowest) * -100
}

/**
 * The Williams percent range (%R), which measures overbought and oversold levels.
 *
 * **See:** [Investopedia](https://www.investopedia.com/terms/w/williamsr.asp)
 *
 * @param data Market data
 * @param n Number of bars (length)
 * @sample vista.indicators.WilliamsPercentRangeTest.withIntSeries
 */
fun williams(data: Data, n: Int = 14) = williams(close(data), high(data), low(data), n)