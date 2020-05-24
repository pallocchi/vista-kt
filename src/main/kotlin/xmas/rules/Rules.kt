package xmas.rules

import xmas.series.Series
import xmas.series.cross
import xmas.series.crossesOver
import xmas.series.crossesUnder

/**
 * Returns if two series has crossed each other (same as `x cross y`).
 *
 * @see Series.cross
 */
fun cross(x: Series, y: Series) = x cross y

/**
 * Returns if [x] series has crossed over the [y] series (same as `x crossOver y`).
 *
 * @see Series.crossesOver
 */
fun crossover(x: Series, y: Series) = x crossesOver y

/**
 * Returns if [x] series has crossed under the [y] series (same as `x crossUnder y`).
 *
 * @see Series.crossesUnder
 */
fun crossunder(x: Series, y: Series) = x crossesUnder y
