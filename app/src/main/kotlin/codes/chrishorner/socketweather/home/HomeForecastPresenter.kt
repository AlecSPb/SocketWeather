package codes.chrishorner.socketweather.home

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import codes.chrishorner.socketweather.R
import codes.chrishorner.socketweather.data.Forecast
import codes.chrishorner.socketweather.util.formatAsDegrees
import codes.chrishorner.socketweather.util.getWeatherIconFor

class HomeForecastPresenter(view: View) {

  private val context: Context = view.context
  private val currentIcon: ImageView = view.findViewById(R.id.home_currentIcon)
  private val currentTemp: TextView = view.findViewById(R.id.home_currentTemp)
  private val feelsLikeTemp: TextView = view.findViewById(R.id.home_feelsLikeTemp)
  private val highTemp: TextView = view.findViewById(R.id.home_highTemp)
  private val lowTemp: TextView = view.findViewById(R.id.home_lowTemp)
  private val description: TextView = view.findViewById(R.id.home_description)
  private val timeForecastsView: TimeForecastView = view.findViewById(R.id.home_timeForecasts)
  private val dateForecastsView: DateForecastsView = view.findViewById(R.id.home_dateForecasts)

  fun display(forecast: Forecast) {
    // Wire up temperatures and icon.
    currentIcon.setImageDrawable(context.getWeatherIconFor(forecast.iconDescriptor, forecast.night))
    currentTemp.text = forecast.currentTemp.formatAsDegrees(context)
    feelsLikeTemp.text = forecast.tempFeelsLike?.formatAsDegrees(context) ?: "--"
    highTemp.text = forecast.highTemp.formatAsDegrees(context)
    lowTemp.text = forecast.lowTemp.formatAsDegrees(context)

    // Sort out description.
    val descriptionText = forecast.todayForecast.extended_text ?: forecast.todayForecast.short_text
    description.text = descriptionText
    description.isVisible = descriptionText.isNullOrBlank().not()

    // Display temperature-time graph and upcoming date forecasts.
    timeForecastsView.display(forecast)
    dateForecastsView.display(forecast)
  }
}
