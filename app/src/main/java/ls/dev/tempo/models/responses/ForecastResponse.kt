package ls.dev.tempo.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ls.dev.tempo.models.responses.CurrentWeatherResponse

@Serializable
data class ForecastResponse(
    val latitude: Double,
    val longitude: Double,
    @SerialName("current_weather") val currentWeather: CurrentWeatherResponse,
    val daily: DailyTemperatureResponse
)
