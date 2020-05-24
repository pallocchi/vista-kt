package xmas.series

import xmas.math.Num

/**
 * Series of numbers.
 */
abstract class Series {

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
