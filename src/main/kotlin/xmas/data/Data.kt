package xmas.data

import xmas.series.Series
import xmas.math.Num

/**
 * Market data.
 */
class Data(
    val bars: List<Bar> = mutableListOf()
) {
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
    )
}

/**
 * Series of prices.
 */
private abstract class Base(
    private val data: Data
) : Series() {

    override fun size(): Int = data.bars.size
}

/**
 * Series of open prices.
 */
private class Open(
    private val data: Data
) : Base(data) {

    override fun get(i: Int): Num {
        return data.bars[i].open;
    }
}

/**
 * Series of high prices.
 */
private class High(
    private val data: Data
) : Base(data) {

    override fun get(i: Int): Num {
        return data.bars[i].high;
    }
}

/**
 * Series of low prices.
 */
private class Low(
    private val data: Data
) : Base(data) {

    override fun get(i: Int): Num {
        return data.bars[i].low;
    }
}

/**
 * Series of close prices.
 */
private class Close(
    private val data: Data
) : Base(data) {

    override fun get(i: Int): Num {
        return data.bars[i].close;
    }
}

/**
 * Series of volume values.
 */
private class Volume(
    private val data: Data
) : Base(data) {

    override fun get(i: Int): Num {
        return data.bars[i].volume;
    }
}

/**
 * The open price series.
 */
fun open(data: Data): Series = Close(data)

/**
 * The high price series.
 */
fun high(data: Data): Series = High(data)

/**
 * The low price series.
 */
fun low(data: Data): Series = Low(data)

/**
 * The close price series.
 */
fun close(data: Data): Series = Close(data)

/**
 * The volume series.
 */
fun volume(data: Data): Series = Volume(data)