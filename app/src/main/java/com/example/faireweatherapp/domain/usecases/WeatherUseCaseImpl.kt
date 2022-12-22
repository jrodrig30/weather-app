package com.example.faireweatherapp.domain.usecases

import com.example.faireweatherapp.core.network.ResponseAny
import com.example.faireweatherapp.core.network.ResponseEmpty
import com.example.faireweatherapp.core.network.ResponseError
import com.example.faireweatherapp.core.network.ResponseSuccess
import com.example.faireweatherapp.data.repositories.WeatherRepository
import com.example.faireweatherapp.domain.model.responses.WeatherInfoResponse

class WeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : WeatherUseCase {
    override suspend fun invoke(locationId: Int): ResponseAny<WeatherInfoResponse> {
        return when (val result = weatherRepository.getWeatherInfo(locationId)) {
            is ResponseSuccess -> setLastWeatherInfo(result)
            is ResponseError -> result
            is ResponseEmpty -> result
        }
    }

    private fun setLastWeatherInfo(result: ResponseSuccess<WeatherInfoResponse>): ResponseAny<WeatherInfoResponse> {
        result.body.lastWeatherInfo = result.body.weatherInfoResponseList[result.body.weatherInfoResponseList.lastIndex]
        return result
    }

}