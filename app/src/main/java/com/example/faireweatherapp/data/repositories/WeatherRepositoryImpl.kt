package com.example.faireweatherapp.data.repositories

import com.example.faireweatherapp.core.network.apiCall
import com.example.faireweatherapp.data.remote.WeatherApi

class WeatherRepositoryImpl(private val api: WeatherApi) : WeatherRepository {
    override suspend fun getWeatherInfo(locationId: Int) = apiCall { api.getWeatherDeitaledInfo(locationId) }
}