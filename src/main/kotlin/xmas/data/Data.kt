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

package xmas.data

import xmas.math.NaN
import xmas.math.Num
import xmas.math.numOf
import xmas.series.Series

/**
 * Market data.
 *
 * @sample xmas.data.DataTest.init
 */
class Data(
    val bars: List<Bar> = mutableListOf()
) {
    /**
     * Returns the bars count.
     */
    fun size(): Int = bars.size

    /**
     * Market data at specific time.
     */
    data class Bar(
        val date: String,
        val open: Num,
        val high: Num,
        val low: Num,
        val close: Num,
        val volume: Num
    ) {
        /**
         * Constructor for [Int] arguments.
         */
        constructor(date: String, open: Int, high: Int, low: Int, close: Int, volume: Int) :
                this(date, numOf(open), numOf(high), numOf(low), numOf(close), numOf(volume))

        /**
         * Constructor for [Long] arguments.
         */
        constructor(date: String, open: Long, high: Long, low: Long, close: Long, volume: Long) :
                this(date, numOf(open), numOf(high), numOf(low), numOf(close), numOf(volume))

        /**
         * Constructor for [Double] arguments.
         */
        constructor(date: String, open: Double, high: Double, low: Double, close: Double, volume: Double) :
                this(date, numOf(open), numOf(high), numOf(low), numOf(close), numOf(volume))

        /**
         * Constructor for [String] arguments.
         */
        constructor(date: String, open: String, high: String, low: String, close: String, volume: String) :
                this(date, numOf(open), numOf(high), numOf(low), numOf(close), numOf(volume))
    }
}

/**
 * Series of prices.
 */
private abstract class Base(
    private val data: Data
) : Series() {

    override operator fun get(i: Int): Num {
        val index = size() - i - 1
        if (index in 0..size())
            return project(index)
        return NaN
    }

    override fun size(): Int = data.bars.size

    abstract fun project(i: Int): Num
}

/**
 * Series of open prices.
 */
private class Open(
    private val data: Data
) : Base(data) {

    override fun project(i: Int): Num {
        return data.bars[i].open;
    }
}

/**
 * Series of high prices.
 */
private class High(
    private val data: Data
) : Base(data) {

    override fun project(i: Int): Num {
        return data.bars[i].high;
    }
}

/**
 * Series of low prices.
 */
private class Low(
    private val data: Data
) : Base(data) {

    override fun project(i: Int): Num {
        return data.bars[i].low;
    }
}

/**
 * Series of close prices.
 */
private class Close(
    private val data: Data
) : Base(data) {

    override fun project(i: Int): Num {
        return data.bars[i].close;
    }
}

/**
 * Series of volume values.
 */
private class Volume(
    private val data: Data
) : Base(data) {

    override fun project(i: Int): Num {
        return data.bars[i].volume;
    }
}

/**
 * The open price series.
 *
 * @sample xmas.data.DataTest.init
 * @sample xmas.data.DataTest.withOpenSeries
 */
fun open(data: Data): Series = Open(data)

/**
 * The high price series.
 *
 * @sample xmas.data.DataTest.init
 * @sample xmas.data.DataTest.withHighSeries
 */
fun high(data: Data): Series = High(data)

/**
 * The low price series.
 *
 * @sample xmas.data.DataTest.init
 * @sample xmas.data.DataTest.withLowSeries
 */
fun low(data: Data): Series = Low(data)

/**
 * The close price series.
 *
 * @sample xmas.data.DataTest.init
 * @sample xmas.data.DataTest.withCloseSeries
 */
fun close(data: Data): Series = Close(data)

/**
 * The volume series.
 *
 * @sample xmas.data.DataTest.init
 * @sample xmas.data.DataTest.withVolumeSeries
 */
fun volume(data: Data): Series = Volume(data)