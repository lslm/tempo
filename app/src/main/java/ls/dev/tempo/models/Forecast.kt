package ls.dev.tempo.models

data class Forecast(
    val currentTemperature: Double,
    val futureForecasts: List<FutureForecast>
)
