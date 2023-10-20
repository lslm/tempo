package ls.dev.tempo.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ls.dev.tempo.models.FutureForecast
import ls.dev.tempo.R

class FutureForecastAdapter(
    private val context: Context,
    forecast: List<FutureForecast>
) : RecyclerView.Adapter<FutureForecastAdapter.FutureForecastViewHolder>() {

    private val dataSet = forecast.toMutableList()

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
        return dataSet.size
    }

    override fun onBindViewHolder(holder: FutureForecastViewHolder, position: Int) {
        val forecast = dataSet[position]
        holder.attach(forecast)
    }

    fun update(futureForecasts: List<FutureForecast>) {
        dataSet.clear()
        dataSet.addAll(futureForecasts)
        notifyDataSetChanged()
    }
}
