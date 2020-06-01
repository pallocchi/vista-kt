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

package xmas.indicators

import xmas.math.NaN
import xmas.math.Num
import xmas.series.Series

/**
 * Series of indicator values.
 */
internal abstract class Indicator(private val source: Series) : Series() {

    override val time: Int get() = source.time

    /**
     * Returns the calculated indicator value [i] bars from now.
     */
    override fun get(i: Int): Num = calculate(i)

    /**
     * Returns the calculated indicator value [i] bars from now.
     */
    abstract fun calculate(i: Int): Num
}

/**
 * Indicator that caches the already calculated values.
 */
internal abstract class CachedIndicator(private val source: Series) : Indicator(source) {

    /**
     * The last time the [cache] was updated.
     */
    private var lastCachedTime: Int = -1

    /**
     * Holds the already calculated values.
     */
    private val cache: MutableList<Num> = mutableListOf()

    /**
     * Returns the calculated indicator value [i] bars from now.
     */
    override fun get(i: Int): Num {
        // calculate how many bars this cache is outdated
        val misses = time - lastCachedTime
        return if (i >= misses) {
            // the requested value is older than the latest cached value,
            // so we can retrieve it from directly from the cache
            cache[time - i]
        } else {
            // the requested value is missing, so we proceed to calculate
            // the missing values and save them into the cache
            var value: Num = NaN
            for (j in misses - 1 downTo 0) {
                value = calculate(j)
                cache.add(value)
                lastCachedTime++
            }
            value
        }
    }
}