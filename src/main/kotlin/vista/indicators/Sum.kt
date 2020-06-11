package vista.indicators

import vista.math.Num
import vista.series.Series

/**
 * Sum helper indicator.
 */
internal class Sum(
    private val source: Series,
    private val n: Int
) : Indicator(source) {

    override val size: Int get() = source.size + 1 - n

    override fun calculate(i: Int): Num {
        var sum = Num.ZERO
        for (j in 0 until n)
            sum += source[i + j]
        return sum
    }
}

/**
 * The sum of last [n] values of [source].
 *
 * @param source Series of values to process
 * @param n Number of bars (length)
 * @sample vista.indicators.SumTest.withIntSeries
 */
fun sum(source: Series, n: Int = 9): Series = if (n > 1) Sum(source, n) else source
