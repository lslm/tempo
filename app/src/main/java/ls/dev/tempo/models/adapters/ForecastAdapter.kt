package ls.dev.tempo.models.adapters

import android.os.Build
import androidx.annotation.RequiresApi
import ls.dev.tempo.models.Forecast
import ls.dev.tempo.models.FutureForecast
import ls.dev.tempo.models.responses.DailyTemperatureResponse
import ls.dev.tempo.models.responses.ForecastResponse
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class ForecastAdapter {
    fun toForecast(forecastResponse: ForecastResponse): Forecast {
        val futureForecasts = buildFutureForecasts(forecastResponse.daily)

        return Forecast(
            currentTemperature = forecastResponse.currentWeather.temperature,
            futureForecasts = futureForecasts
        )
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun buildFutureForecasts(dailyTemperatureResponse: DailyTemperatureResponse): List<FutureForecast> {
        return dailyTemperatureResponse.time.take(5).mapIndexed { index, day ->
            val date = LocalDate.parse(day)
            val brLocale = Locale("pt", "BR")

            val minTemperature = dailyTemperatureResponse.minTemperatures[index]
            val maxTemperature = dailyTemperatureResponse.maxTemperatures[index]

            FutureForecast(
                day = date.dayOfWeek.getDisplayName(TextStyle.SHORT, brLocale).capitalize(),
                maxTemperature = maxTemperature,
                minTemperature = minTemperature
            )
        }
    }
}
