package com.example.faireweatherapp.domain.model.responses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class WeatherInfoResponse(
    @SerializedName("title") var city: String,
    @SerializedName("consolidated_weather") var weatherInfoResponseList: ArrayList<WeatherInfoDetailedResponse>,
    var lastWeatherInfo: WeatherInfoDetailedResponse?
) {

    @Keep
    data class WeatherInfoDetailedResponse(
        @SerializedName("weather_state_name") var weatherStateName: String,
        @SerializedName("weather_state_abbr") var weatherStateAbbr: String,
        @SerializedName("min_temp") var minTemp: String,
        @SerializedName("max_temp") var maxTemp: String,
        @SerializedName("the_temp") var theTemp: String,
    )
}
