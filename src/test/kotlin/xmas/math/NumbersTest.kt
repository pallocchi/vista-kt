package xmas.math

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class NumbersTest {

    @Test
    fun plus() {
        val one = numOf(1)

        assertThat(one + one).isEqualTo(numOf(2))
        assertThat(one + NaN).isEqualTo(NaN)
        assertThat(NaN + one).isEqualTo(NaN)
    }

    @Test
    fun minus() {
        val one = numOf(1)

        assertThat(one - one).isEqualTo(numOf(0))
        assertThat(one - NaN).isEqualTo(NaN)
        assertThat(NaN - one).isEqualTo(NaN)
    }

    @Test
    fun times() {
        val one = numOf(1)

        assertThat(one * one).isEqualTo(numOf(1))
        assertThat(one * NaN).isEqualTo(NaN)
        assertThat(NaN * one).isEqualTo(NaN)
    }

    @Test
    fun div() {
        val one = numOf(1)

        assertThat(one / one).isEqualTo(numOf(1))
        assertThat(one / NaN).isEqualTo(NaN)
        assertThat(NaN / one).isEqualTo(NaN)
    }

    @Test
    fun compare() {
        val one = numOf(1)
        val two = numOf(2)

        assertThat(one.compareTo(two)).isNegative()
        assertThat(two.compareTo(one)).isPositive()
        assertThat(one.compareTo(NaN)).isZero()
        assertThat(NaN.compareTo(one)).isZero()
    }

    @Test
    fun toInt() {
        assertThat(numOf(1).toInt()).isEqualTo(1)
        assertThat(numOf(1L).toInt()).isEqualTo(1)
        assertThat(numOf(1.0).toInt()).isEqualTo(1)
    }

    @Test
    fun toLong() {
        assertThat(numOf(1).toLong()).isEqualTo(1)
        assertThat(numOf(1L).toLong()).isEqualTo(1)
        assertThat(numOf(1.0).toLong()).isEqualTo(1)
    }

    @Test
    fun toDouble() {
        assertThat(numOf(1).toDouble()).isEqualTo(1.0)
        assertThat(numOf(1L).toDouble()).isEqualTo(1.0)
        assertThat(numOf(1.0).toDouble()).isEqualTo(1.0)
    }

    @Test
    fun toFloat() {
        assertThat(numOf(1).toFloat()).isEqualTo(1.0F)
        assertThat(numOf(1L).toFloat()).isEqualTo(1.0F)
        assertThat(numOf(1.0).toFloat()).isEqualTo(1.0F)
    }

    @Test
    fun na() {
        assertThat(na).isEqualTo(NaN)
    }
}
