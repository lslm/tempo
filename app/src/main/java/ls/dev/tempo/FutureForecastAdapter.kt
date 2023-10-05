package ls.dev.tempo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FutureForecastAdapter(
    private val context: Context,
    val forecast: List<FutureForecast>
) : RecyclerView.Adapter<FutureForecastAdapter.FutureForecastViewHolder>() {

    inner class FutureForecastViewHolder(private val futureForecastView: View) : RecyclerView.ViewHolder(futureForecastView) {
        fun attach(futureForecast: FutureForecast) {
            val dayTextView = futureForecastView.findViewById<TextView>(R.id.future_forecast_day)
            val temperatureTextView = futureForecastView.findViewById<TextView>(R.id.future_forecast_temperature)

            dayTextView.text = futureForecast.day
            temperatureTextView.text = "${futureForecast.temperature}º"
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): FutureForecastViewHolder {
        val futureForecastView = LayoutInflater.from(context).inflate(R.layout.future_forecast, viewGroup, false)
        return FutureForecastViewHolder(futureForecastView)
    }

    override fun getItemCount(): Int {
        return forecast.size
    }

    override fun onBindViewHolder(holder: FutureForecastViewHolder, position: Int) {
        val forecast = forecast[position]
        holder.attach(forecast)
    }
}
