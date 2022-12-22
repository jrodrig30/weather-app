package com.example.faireweatherapp.presentation.ui.weather

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.ExperimentalComposeApi
import com.example.faireweatherapp.core.test.MainCoroutineRule
import com.example.faireweatherapp.core.test.getOrAwaitValue
import com.example.faireweatherapp.core.utils.Status
import com.example.faireweatherapp.domain.usecases.WeatherUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalComposeApi
class WeatherViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var weatherUseCase: WeatherUseCase
    private lateinit var viewModel: WeatherViewModel
    @Before
    fun setUp(){
        weatherUseCase = WeatherUseCaseFake()
        viewModel = WeatherViewModel(weatherUseCase)
    }

    @Test
    fun `when getWeatherInfo returns Success`() = mainCoroutineRule.runBlockingTest {
        viewModel.getWeatherInfo()
        assertThat(viewModel.weatherInfo.getOrAwaitValue()).isNotNull()
        assertThat(viewModel.status.getOrAwaitValue()).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `when getWeatherInfo returns Error`() = mainCoroutineRule.runBlockingTest {
        (weatherUseCase as WeatherUseCaseFake).setReturnError(true)
        viewModel.getWeatherInfo()
        assertThat(viewModel.status.getOrAwaitValue()).isEqualTo(Status.ERROR)
    }

}