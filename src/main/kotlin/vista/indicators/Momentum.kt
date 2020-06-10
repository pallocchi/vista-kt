package vista.indicators

import vista.math.change
import vista.series.Series

/**
 * The momentum indicator, which compares the current price with the previous price from [n] periods ago.
 *
 * **See:** [TradingView](https://www.tradingview.com/ideas/momentum/)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.MomentumTest.withIntSeries
 */
fun mom(source: Series, n: Int = 10) = change(source, n)