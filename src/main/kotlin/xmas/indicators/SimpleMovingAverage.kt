package xmas.indicators

import xmas.math.NaN
import xmas.math.Num
import xmas.math.numOf
import xmas.series.Series

/**
 * Simple Moving Average (SMA) indicator.
 */
internal class SimpleMovingAverage(
    private val source: Series,
    private val n: Int
) : Indicator(source) {

    override fun calculate(index: Int): Num {
        val rIndex = source.size() - index - 1
        if (rIndex in (n - 1)..source.size()) {
            var sum = numOf(0)
            for (i in 0 until n)
                sum += source[rIndex - i]
            return sum / numOf(n)
        }
        return NaN
    }
}

/**
 * The simple moving average, that is the sum of last [n] values of [source], divided by [n].
 *
 * **See:** [Investopedia](https://www.investopedia.com/terms/s/sma.asp)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample xmas.indicators.SimpleMovingAverageTest.withIntSeries
 */
fun sma(source: Series, n: Int): Series = SimpleMovingAverage(source, n)
