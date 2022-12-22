package com.example.faireweatherapp.data.remote

import com.example.faireweatherapp.domain.model.responses.WeatherInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherApi {
 @GET("{locationId}.json")
 suspend fun getWeatherDeitaledInfo(@Path("locationId") locationId: Int): Response<WeatherInfoResponse>
}