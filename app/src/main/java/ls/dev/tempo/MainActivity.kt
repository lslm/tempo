package ls.dev.tempo

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.runBlocking

class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val forecastClient = ForecastClient()
        var forecastResponse: ForecastResponse

        runBlocking {
            forecastResponse = forecastClient.getCurrentWeather()
        }

        val currentTemperatureText = findViewById<TextView>(R.id.temperatura_atual)
        currentTemperatureText.text = "${forecastResponse.currentWeather.temperature}ºC"
    }
}
