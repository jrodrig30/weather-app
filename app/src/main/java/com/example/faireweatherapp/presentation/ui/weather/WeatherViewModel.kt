package com.example.faireweatherapp.presentation.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.faireweatherapp.core.utils.Status
import com.example.faireweatherapp.core.utils.read
import com.example.faireweatherapp.domain.model.responses.WeatherInfoResponse
import com.example.faireweatherapp.domain.usecases.WeatherUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherUseCase: WeatherUseCase) : ViewModel() {
    private val _weatherInfo = MutableLiveData<WeatherInfoResponse>()
    val weatherInfo: LiveData<WeatherInfoResponse> = _weatherInfo

    private val _status =  MutableLiveData<Status>().apply{ value = Status.LOADING }
    val status: LiveData<Status> = _status


    fun getWeatherInfo()  = viewModelScope.launch {
        weatherUseCase(locationIdTest).read(
            success = { apiResponse ->
                _weatherInfo.value = apiResponse
                _status.value = Status.SUCCESS
            },
            error = {
                _status.value = Status.ERROR
            }
        )
    }

    fun resetLoadStatus(){
        _status.value = Status.LOADING
    }

    companion object {
        const val locationIdTest = 4418
    }
}