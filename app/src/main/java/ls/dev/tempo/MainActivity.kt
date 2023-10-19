package ls.dev.tempo

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking

class MainActivity: Activity() {

    val futureForecasts = listOf(
        FutureForecast(day = "TER", temperature = 22.0),
        FutureForecast(day = "QUA", temperature = 25.5),
        FutureForecast(day = "QUI", temperature = 18.3),
        FutureForecast(day = "SEX", temperature = 19.0),
        FutureForecast(day = "SAB", temperature = 23.3),
    )

    private val futureForecastAdapter = FutureForecastAdapter(this, futureForecasts)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        val changeLocationButton = findViewById<Button>(R.id.change_location_button)
        changeLocationButton.setOnClickListener {
            ChangeLocationDialog(this).show { latitude, longitude ->
                updateCurrentWeatherView(latitude, longitude)
            }
        }

        updateCurrentWeatherView("-23.5228", "-46.1883")
    }

    private fun updateCurrentWeatherView(latitude: String, longitude: String) {
        val forecastClient = ForecastClient()
        var forecastResponse: ForecastResponse

        runBlocking {
            forecastResponse = forecastClient.getCurrentWeather(latitude, longitude)
        }

        val currentTemperatureText = findViewById<TextView>(R.id.temperatura_atual)
        currentTemperatureText.text = "${forecastResponse.currentWeather.temperature}ºC"

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = futureForecastAdapter
    }
}
