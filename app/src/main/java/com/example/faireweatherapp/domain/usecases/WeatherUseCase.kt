package com.example.faireweatherapp.domain.usecases

import com.example.faireweatherapp.core.network.ResponseAny
import com.example.faireweatherapp.domain.model.responses.WeatherInfoResponse

interface WeatherUseCase {
    suspend operator fun invoke(locationId: Int): ResponseAny<WeatherInfoResponse>
}