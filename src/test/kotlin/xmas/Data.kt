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

package xmas

import xmas.data.Data
import xmas.data.Data.Bar
import xmas.math.NaN
import xmas.math.Num
import xmas.math.numOf

/**
 * Load the market data from resources folder.
 */
fun loadAmazonData(): Data {
    val bars = mutableListOf<Bar>()
    val csv = Data::class.java.classLoader.getResourceAsStream("amzn.csv")
    csv!!.bufferedReader().useLines { lines -> lines.drop(1).forEach { bars.add(line2bar(it)) } }
    val data = Data(bars.asReversed())
    assert(data.size == 500)
    return data
}

/**
 * Returns the [Bar] parsed from string [line].
 */
private fun line2bar(line: String): Bar {
    val split = line.split(",")
    return Bar(split[0], split[1], split[2], split[3], split[4], split[5])
}

/**
 * Load the indicator values [file] from resources folder.
 */
fun loadIndicatorData(file: String): List<List<Num>> {
    val values = mutableListOf<List<Num>>()
    val csv = Data::class.java.classLoader.getResourceAsStream(file)
    csv!!.bufferedReader().useLines { lines ->
        var size: Int? = null
        for (line in lines) {
            if (size != null) {
                values.add(line2num(line, size))
            } else {
                size = line.split(",").size
            }
        }
    }
    assert(values.size == 100)
    return values
}

/**
 * Returns the [Num] parsed from string [line].
 */
private fun line2num(line: String, size: Int): List<Num> {
    val values = mutableListOf<Num>()
    val split = line.split(",")
    for (i in 1 until size) {
        val value = if (i < split.size) numOf(split[i]) else NaN
        values.add(value)
    }
    return values
}