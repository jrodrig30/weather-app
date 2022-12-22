package com.example.faireweatherapp.data.repositories

import com.example.faireweatherapp.core.network.ResponseAny
import com.example.faireweatherapp.domain.model.responses.WeatherInfoResponse

interface WeatherRepository {
    suspend fun getWeatherInfo(locationId: Int): ResponseAny<WeatherInfoResponse>
}