package ls.dev.tempo.views

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import ls.dev.tempo.clients.ForecastClient
import ls.dev.tempo.models.responses.ForecastResponse
import ls.dev.tempo.models.FutureForecast
import ls.dev.tempo.views.adapters.FutureForecastAdapter
import ls.dev.tempo.R
import ls.dev.tempo.models.Forecast

class MainActivity: Activity() {

    val forecastClient = ForecastClient()

    val futureForecasts = listOf(
        FutureForecast(day = "TER", temperature = 22.0),
        FutureForecast(day = "QUA", temperature = 25.5),
    )

    private val futureForecastAdapter = FutureForecastAdapter(this, futureForecasts)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        configureFutureForecasts()

        val initialForecast = forecastClient.getForecast("-23.5228", "-46.1883")
        updateCurrentWeatherView(initialForecast)
    }

    override fun onResume() {
        super.onResume()

        val changeLocationButton = findViewById<Button>(R.id.change_location_button)
        changeLocationButton.setOnClickListener {
            ChangeLocationDialog(this).show { latitude, longitude ->
                val newForecast = forecastClient.getForecast(latitude, longitude)
                updateCurrentWeatherView(newForecast)
            }
        }
    }

    private fun configureFutureForecasts() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = futureForecastAdapter
    }

    private fun updateCurrentWeatherView(forecast: Forecast) {
        val currentTemperatureText = findViewById<TextView>(R.id.temperatura_atual)
        currentTemperatureText.text = "${forecast.currentTemperature}ºC"
        futureForecastAdapter.update(forecast.futureForecasts)
    }
}
