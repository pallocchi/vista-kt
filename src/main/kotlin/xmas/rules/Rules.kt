package xmas.rules

import xmas.series.Series
import xmas.series.cross
import xmas.series.crossOver
import xmas.series.crossUnder

/**
 * Returns if two series has crossed each other (same as `x cross y`).
 *
 * @see Series.cross
 * @sample xmas.rules.RulesTest.cross
 */
fun cross(x: Series, y: Series) = x cross y

/**
 * Returns if [x] series has crossed over the [y] series (same as `x crossOver y`).
 *
 * @see Series.crossesOver
 * @sample xmas.rules.RulesTest.crossover
 */
fun crossover(x: Series, y: Series) = x crossOver y

/**
 * Returns if [x] series has crossed under the [y] series (same as `x crossUnder y`).
 *
 * @see Series.crossesUnder
 * @sample xmas.rules.RulesTest.crossunder
 */
fun crossunder(x: Series, y: Series) = x crossUnder y
