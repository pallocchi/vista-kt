package xmas.indicators

import xmas.series.Series
import xmas.math.Num
import xmas.math.numOf

/**
 * Simple Moving Average (SMA) indicator.
 */
internal class SimpleMovingAverage(
    private val source: Series,
    private val n: Int
) : Indicator() {

    override fun calculate(index: Int): Num {
        var sum = numOf(0)
        for (i in n downTo 0)
            sum += source[index - i]
        return sum
    }
}

/**
 * The simple moving average, that is the sum of last [n] values of [source], divided by [n].
 *
 * **See:** [Investopedia](https://www.investopedia.com/terms/s/sma.asp)
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample xmas.indicators.SimpleMovingAverageSample.sample
 */
fun sma(source: Series, n: Int): Series = SimpleMovingAverage(source, n)
