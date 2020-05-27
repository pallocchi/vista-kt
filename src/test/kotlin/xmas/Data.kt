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

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import xmas.data.Data
import xmas.data.Data.Bar
import xmas.math.NaN
import xmas.math.Num
import xmas.math.numOf

fun jsonObjectMapper(): ObjectMapper {
    val mapper = jacksonObjectMapper()
    val module = SimpleModule()
    module.addDeserializer(Num::class.java, NumDeserializer())
    mapper.registerModule(module)
    return mapper
}

fun loadAmazonData(): Data {
    val json = Data::class.java.classLoader.getResourceAsStream("data.json")
    val bars: MutableList<Bar> = jsonObjectMapper().readValue(json)
    return Data(bars)
}

fun loadIndicatorData(file: String): List<IndicatorValue> {
    val json = Data::class.java.classLoader.getResourceAsStream(file)
    val values: List<IndicatorValue> = jsonObjectMapper().readValue(json)
    values.forEach { it.value = it.value ?: NaN }
    return values.reversed()
}

/**
 * Single indicator value.
 */
data class IndicatorValue(val date: String, var value: Num?)

/**
 * Custom deserializer for [Num] types.
 */
private class NumDeserializer : JsonDeserializer<Num>() {

    override fun deserialize(jp: JsonParser, ctx: DeserializationContext): Num {
        val value: String? = jp.valueAsString
        if (value != null)
            return numOf(value)
        return NaN
    }
}
