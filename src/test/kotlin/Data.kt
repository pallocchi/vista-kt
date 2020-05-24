import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import xmas.data.Data
import xmas.data.Data.Bar
import java.math.BigDecimal

fun loadAmazonData(): Data {
    val json = Data::class.java.classLoader.getResourceAsStream("data.json")
    val bars: List<Bar> = jacksonObjectMapper().readValue(json)
    return Data(bars)
}

fun loadIndicatorData(file: String): List<IndicatorValue> {
    val json = Data::class.java.classLoader.getResourceAsStream(file)
    return jacksonObjectMapper().readValue(json)
}

data class IndicatorValue(val date: String, val value: BigDecimal?)