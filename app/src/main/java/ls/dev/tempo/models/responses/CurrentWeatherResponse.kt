package ls.dev.tempo.models.responses

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherResponse(
    val temperature: Double
)
