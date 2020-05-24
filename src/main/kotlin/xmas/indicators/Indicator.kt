package xmas.indicators

import xmas.math.Num
import xmas.series.Series

/**
 * Series of indicator values.
 */
internal abstract class Indicator(private val source: Series) : Series() {

    override fun get(i: Int): Num = calculate(i)

    override fun size(): Int = source.size()

    abstract fun calculate(index: Int): Num
}
