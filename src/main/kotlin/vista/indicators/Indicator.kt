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

package vista.indicators

import vista.math.NaN
import vista.math.Num
import vista.series.Series

/**
 * Series of indicator values.
 */
internal abstract class Indicator(private val source: Series) : Series {

    override val time: Int get() = source.time

    /**
     * Returns the calculated indicator value [i] bars from now.
     */
    override fun get(i: Int): Num = if (i in 0 until size) calculate(i) else NaN

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
        return when (i) {
            in misses until size -> cache[time - i]     // value is in cache
            in 0 until misses -> calculate(misses, i)   // value is missing
            else -> NaN                                 // value out of range
        }
    }

    /**
     * Calculates the missing values from the [start] to the [end], and return the last one.
     */
    private fun calculate(start: Int, end: Int): Num {
        var value: Num = NaN
        for (j in start - 1 downTo end) {
            value = calculate(j)
            cache.add(value)
            lastCachedTime++
        }
        return value
    }
}