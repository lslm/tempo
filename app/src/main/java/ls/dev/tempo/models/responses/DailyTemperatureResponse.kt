package ls.dev.tempo.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyTemperatureResponse(
    val time: List<String>,
    @SerialName("temperature_2m_max") val maxTemperatures: List<Double>,
    @SerialName("temperature_2m_min") val minTemperatures: List<Double>
)
