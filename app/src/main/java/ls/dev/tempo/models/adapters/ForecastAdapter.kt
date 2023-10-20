package ls.dev.tempo.models.adapters

import ls.dev.tempo.models.Forecast
import ls.dev.tempo.models.FutureForecast
import ls.dev.tempo.models.responses.ForecastResponse

class ForecastAdapter {
    fun toForecast(forecastResponse: ForecastResponse): Forecast {
        val futureForecasts = listOf(
            FutureForecast(day = "TER", temperature = 30.0),
            FutureForecast(day = "QUA", temperature = 30.0),
            FutureForecast(day = "QUI", temperature = 30.0),
            FutureForecast(day = "SEX", temperature = 30.0),
        )

        return Forecast(
            currentTemperature = forecastResponse.currentWeather.temperature,
            futureForecasts = futureForecasts
        )
    }
}
