package com.example.faireweatherapp.presentation.ui.weather

import com.example.faireweatherapp.core.network.ResponseAny
import com.example.faireweatherapp.core.network.ResponseError
import com.example.faireweatherapp.core.network.ResponseSuccess
import com.example.faireweatherapp.domain.model.responses.WeatherInfoResponse
import com.example.faireweatherapp.domain.usecases.WeatherUseCase
import java.io.IOException

class WeatherUseCaseFake : WeatherUseCase {
    private var shouldReturnNotFound = false

    fun setReturnError(value: Boolean) {
        shouldReturnNotFound = value
    }

    override suspend fun invoke(locationId: Int): ResponseAny<WeatherInfoResponse> {
        return if (shouldReturnNotFound) ResponseError(IOException("")) else ResponseSuccess(
            weatherInfoResponseData
        )
    }
}

val weatherInfoResponseData = WeatherInfoResponse(
    "Toronto",
    lastWeatherInfo = WeatherInfoResponse.WeatherInfoDetailedResponse(
        weatherStateName = "Light Rain",
        weatherStateAbbr = "lr",
        minTemp = "13.895",
        maxTemp = "22.24",
        theTemp = "22.33"
    ),
    weatherInfoResponseList = arrayListOf<WeatherInfoResponse.WeatherInfoDetailedResponse>(
        WeatherInfoResponse.WeatherInfoDetailedResponse(
            weatherStateName = "Light Cloud",
            weatherStateAbbr = "lc",
            minTemp = "15.895",
            maxTemp = "26.24",
            theTemp = "24.33"
        ),
        WeatherInfoResponse.WeatherInfoDetailedResponse(
            weatherStateName = "Light Rain",
            weatherStateAbbr = "lr",
            minTemp = "13.895",
            maxTemp = "22.24",
            theTemp = "22.33"
        )
    )
)