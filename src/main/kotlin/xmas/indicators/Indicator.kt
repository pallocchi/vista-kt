package xmas.indicators

import xmas.math.Num
import xmas.series.Series

/**
 * Series of indicator values.
 */
internal abstract class Indicator : Series() {

    override fun get(i: Int): Num = calculate(i)

    abstract fun calculate(index: Int): Num
}