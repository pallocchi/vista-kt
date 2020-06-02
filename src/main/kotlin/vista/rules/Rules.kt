/*
 * MIT License
 *
 * Copyright (c) 2020 Pablo Pallocchi
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
 * @see Series.crossOver
 * @sample xmas.rules.RulesTest.crossover
 */
fun crossover(x: Series, y: Series) = x crossOver y

/**
 * Returns if [x] series has crossed under the [y] series (same as `x crossUnder y`).
 *
 * @see Series.crossUnder
 * @sample xmas.rules.RulesTest.crossunder
 */
fun crossunder(x: Series, y: Series) = x crossUnder y
