package xmas.series

import xmas.math.NaN
import xmas.math.Num
import xmas.math.numOf

/**
 * Returns a [Num] series from given [values].
 */
fun seriesOf(vararg values: Num): Series = SimpleSeries(mutableListOf(*values))

/**
 * Returns a [Num] series from given [Int] values.
 */
fun seriesOf(vararg values: Int): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Returns a [Num] series from given [Long] values.
 */
fun seriesOf(vararg values: Long): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Returns a [Num] series from given [Double] values.
 */
fun seriesOf(vararg values: Double): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Returns a [Num] series from given [String] values.
 */
fun seriesOf(vararg values: String): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Series of numbers.
 */
abstract class Series {

    /**
     * Returns the series size.
     */
    abstract fun size(): Int

    /**
     * Returns the series value at given index.
     */
    abstract operator fun get(i: Int): Num
}

/**
 * Returns if two series has crossed each other.
 *
 * @sample xmas.series.SeriesTest.cross
 */
infix fun Series.cross(other: Series) = this crossOver other || this crossUnder other

/**
 * Returns if current series has crossed over the [other].
 *
 * @sample xmas.series.SeriesTest.crossOver
 */
infix fun Series.crossOver(other: Series) = this[0] > other[0] && this[1] < other[1]

/**
 * Returns if current series has crossed under the [other].
 *
 * @sample xmas.series.SeriesTest.crossUnder
 */
infix fun Series.crossUnder(other: Series) = this[0] < other[0] && this[1] > other[1]

/**
 * Basic series of numbers implementation.
 */
internal class SimpleSeries(private val values: List<Num>) : Series() {

    override fun size(): Int = values.size

    override operator fun get(i: Int): Num {
        val index = size() - i - 1
        if (index in 0..size())
            return values[index]
        return NaN
    }
}
