package com.example.faireweatherapp.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.example.faireweatherapp.core.utils.Status
import com.example.faireweatherapp.presentation.ui.theme.FaireWeatherAppTheme
import com.example.faireweatherapp.presentation.ui.weather.WeatherHomeScreen
import com.example.faireweatherapp.presentation.ui.weather.WeatherViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FaireWeatherAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    initializeApp()
                }
            }
        }
    }

    @Composable
    private fun initializeApp() {
        var viewModel: WeatherViewModel = getViewModel<WeatherViewModel>()
        LaunchedEffect(viewModel.status.value){
            delay(1000)
            viewModel.getWeatherInfo()
        }

        val apiRequestStatus by viewModel.status.observeAsState()
        when(apiRequestStatus){
            Status.SUCCESS -> WeatherHomeScreen()
            Status.LOADING -> LandingScreen(Modifier)
            else -> {
                    CustomDialog(
                        title = "Error",
                        message = "Oops, something wrong happened! Please, try again.",
                        onPositiveButtonClicked = {
                            viewModel.resetLoadStatus()
                        },
                        onDismiss = {
                           viewModel.resetLoadStatus()
                        },
                        properties = DialogProperties(
                            dismissOnBackPress = false,
                            dismissOnClickOutside = false
                        )
                    )
            }
        }
    }


}


