package ls.dev.tempo

import io.ktor.client.call.body
import io.ktor.client.request.get

class ForecastClient {
    suspend fun getCurrentWeather() : ForecastResponse {
        return KtorClient.httpClient.get(
            "https://api.open-meteo.com/v1/forecast?latitude=-23.5228&longitude=-46.1883&current_weather=true"
        ).body()
    }
}
