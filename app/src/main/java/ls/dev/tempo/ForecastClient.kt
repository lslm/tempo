package ls.dev.tempo

import io.ktor.client.call.body
import io.ktor.client.request.get

class ForecastClient {
    suspend fun getCurrentWeather(latitude: String, longitude: String) : ForecastResponse {
        return KtorClient.httpClient.get(
            "https://api.open-meteo.com/v1/forecast?latitude=${latitude}&longitude=${longitude}&current_weather=true"
        ).body()
    }
}
