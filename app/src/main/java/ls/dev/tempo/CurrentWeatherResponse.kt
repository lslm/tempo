package ls.dev.tempo

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse(
    val temperature: Double
)
