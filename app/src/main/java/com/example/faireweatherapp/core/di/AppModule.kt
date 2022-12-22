package com.example.faireweatherapp.core.di

import com.example.faireweatherapp.core.network.RetrofitClient.createWebService
import com.example.faireweatherapp.data.remote.WeatherApi
import com.example.faireweatherapp.data.repositories.WeatherRepository
import com.example.faireweatherapp.data.repositories.WeatherRepositoryImpl
import com.example.faireweatherapp.domain.usecases.WeatherUseCase
import com.example.faireweatherapp.domain.usecases.WeatherUseCaseImpl
import com.example.faireweatherapp.presentation.ui.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object AppModule {

    fun allModules() = listOf(viewModel, useCasesModule, dataModule)

    private val viewModel = module {
        viewModel {
            WeatherViewModel(weatherUseCase = get())
        }
    }

    private val useCasesModule = module {
        single<WeatherUseCase> {
            WeatherUseCaseImpl(weatherRepository = get())
        }
    }

    private val dataModule = module {
        single {
            createWebService<WeatherApi>()
        }

        factory<WeatherRepository> {
            WeatherRepositoryImpl(api = get())
        }
    }
}
