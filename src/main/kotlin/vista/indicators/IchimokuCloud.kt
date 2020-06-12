package vista.indicators

import vista.series.Series

/**
 * The Ichimoku Cloud series.
 */
data class IchimokuCloud(
    /** Tenkan-Sen (Conversion Line) **/
    val ts: Series,
    /** Kijun-Sen (Base Line) **/
    val ks: Series,
    /** Senkou Span A (Leading Span A) **/
    val ssa: Series,
    /** Senkou Span B (Leading Span B) **/
    val ssb: Series
)

/**
 * The Ichimoku Cloud, which shows support and resistance levels, as well as momentum and trend direction.
 *
 * Returns the Tenkan-Sen (Conversion Line), Kijun-Sen (Base Line), Senkou Span A (Leading Span A), and Senkou Span B (Leading Span B).
 *
 * **See:** [Vista Docs](https://bulltimate.github.io/vista/#/ichimoku)
 *
 * @param high Series of high values
 * @param low Series of low values
 * @param tsLength Number of bars (length) used by Tenkan-Sen
 * @param ksLength Number of bars (length) used by Kijun-Sen
 * @param ssLength Number of bars (length) used by Senkou Span
 * @sample vista.indicators.IchimokuCloudTest.withIntSeries
 */
fun ichimoku(
    high: Series,
    low: Series,
    tsLength: Int = 9,
    ksLength: Int = 26,
    ssLength: Int = 52,
    displacement: Int = 26
): IchimokuCloud {
    val avg: (Int) -> Series = { (highest(high, it) + lowest(low, it)) / 2 }
    val ts = avg(tsLength)
    val ks = avg(ksLength)
    val ssa = (ts + ks) / 2
    val ssb = avg(ssLength)
    val d = displacement - 1
    return IchimokuCloud(ts, ks, ssa(d), ssb(d))
}