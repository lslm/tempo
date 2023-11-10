package ls.dev.tempo.clients

import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.runBlocking
import ls.dev.tempo.models.Forecast
import ls.dev.tempo.models.adapters.ForecastAdapter
import ls.dev.tempo.models.responses.ForecastResponse

class ForecastClient {
    private val forecastAdapter = ForecastAdapter()

    private suspend fun callCurrentForecastAPI(latitude: String, longitude: String) : ForecastResponse {
        return KtorClient.httpClient.get(
            "https://api.open-meteo.com/v1/forecast?latitude=${latitude}&longitude=${longitude}&current_weather=true&daily=temperature_2m_max,temperature_2m_min"
        ).body()
    }

    fun getForecast(latitude: String, longitude: String) : Forecast {
        val forecastResponse: ForecastResponse
        runBlocking {
            forecastResponse = callCurrentForecastAPI(latitude, longitude)
        }

        println(forecastResponse)

        return forecastAdapter.toForecast(forecastResponse)
    }
}
