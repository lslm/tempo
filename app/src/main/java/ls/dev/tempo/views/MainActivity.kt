package ls.dev.tempo.views

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.runBlocking
import ls.dev.tempo.clients.ForecastClient
import ls.dev.tempo.models.responses.ForecastResponse
import ls.dev.tempo.models.FutureForecast
import ls.dev.tempo.views.adapters.FutureForecastAdapter
import ls.dev.tempo.R
import ls.dev.tempo.models.Forecast
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class MainActivity: Activity() {

    val forecastClient = ForecastClient()

    val futureForecasts = listOf(
        FutureForecast(day = "TER", maxTemperature = 22.0, minTemperature = 0.0),
        FutureForecast(day = "QUA", maxTemperature = 22.0, minTemperature = 0.0),
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun updateCurrentWeatherView(forecast: Forecast) {
        val currentDate = LocalDate.now()

        val currentDateTextView = findViewById<TextView>(R.id.current_date)
        val currentTemperatureText = findViewById<TextView>(R.id.temperatura_atual)

        currentDateTextView.text = currentDate.dayOfWeek.getDisplayName(TextStyle., Locale("pt", "BR"))
        currentTemperatureText.text = "${forecast.currentTemperature}ºC"
        futureForecastAdapter.update(forecast.futureForecasts)
    }
}
