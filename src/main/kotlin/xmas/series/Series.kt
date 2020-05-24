package xmas.series

import xmas.math.Num
import xmas.math.numOf

/**
 * Returns a series of [Num] numbers.
 */
fun seriesOf(vararg values: Num): Series = SimpleSeries(mutableListOf(*values))

/**
 * Returns a series of [Int] numbers.
 */
fun seriesOf(vararg values: Int): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Returns a series of [Long] numbers.
 */
fun seriesOf(vararg values: Long): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Returns a series of [Double] numbers.
 */
fun seriesOf(vararg values: Double): Series = SimpleSeries(values.map { numOf(it) })

/**
 * Returns a series of [String] numbers.
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
    abstract operator fun get(i: Int): Num;
}

/**
 * Returns if two series has crossed each other.
 */
infix fun Series.cross(other: Series) = false

/**
 * Returns if current series has crossed over the [other].
 */
infix fun Series.crossesOver(other: Series) = false

/**
 * Returns if current series has crossed under the [other].
 */
infix fun Series.crossesUnder(other: Series) = false

/**
 * Basic series of numbers implementation.
 */
internal class SimpleSeries(private val values: List<Num>) : Series() {

    override fun get(i: Int): Num = values[i]

    override fun size(): Int = values.size
}
