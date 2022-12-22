package com.example.faireweatherapp.presentation.ui.weather

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.faireweatherapp.BuildConfig
import com.example.faireweatherapp.R
import com.example.faireweatherapp.presentation.ui.theme.FaireWeatherAppTheme
import com.example.faireweatherapp.presentation.ui.theme.Typography
import com.example.faireweatherapp.presentation.ui.theme.backgroundDarkThemeColor
import com.example.faireweatherapp.presentation.ui.theme.backgroundLightThemeColor
import com.skydoves.landscapist.glide.GlideImage
import org.koin.androidx.compose.getViewModel


@Composable
fun WeatherHomeScreen(
    modifier: Modifier = Modifier, viewModel: WeatherViewModel = getViewModel<WeatherViewModel>()
) {
    val backgroundColor =
        if (isSystemInDarkTheme()) backgroundDarkThemeColor else backgroundLightThemeColor

    Box(modifier = modifier.background(Brush.linearGradient(backgroundColor))) {
        Column(
            modifier = Modifier.padding(vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row {
                Text(
                    text = viewModel.weatherInfo.value?.city.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )

                Image(
                    painter = painterResource(id = R.drawable.city),
                    contentDescription = "city icon",
                    modifier = Modifier.height(50.dp)

                )
            }

            GlideImage(
                imageModel = { getRemoteImage(viewModel) },
                modifier = Modifier
                    .padding(1.dp)
                    .size(90.dp)
            )

            Text(
                text = getCurrentTemp(viewModel, stringResource(id = R.string.temperature_symbol)),
                fontSize = 50.sp
            )

            Text(
                text = viewModel.weatherInfo.value?.lastWeatherInfo?.weatherStateName.toString(),
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                textAlign = TextAlign.Center,
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = stringResource(id = R.string.lowest_temperature_label) + ": " + getLowestTemperature(
                        viewModel, stringResource(id = R.string.temperature_symbol)
                    ),
                    style = Typography.labelSmall,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )

                Text(
                    text = stringResource(id = R.string.highest_temperature_label) + ": " + getHighestTemperature(
                        viewModel, stringResource(id = R.string.temperature_symbol)
                    ),
                    style = Typography.labelSmall,
                )
            }
        }
    }

}


fun getHighestTemperature(viewModel: WeatherViewModel, symbol: String) =
    "${viewModel.weatherInfo.value?.lastWeatherInfo?.maxTemp.toString().substring(0, 2)}${symbol}"

fun getLowestTemperature(viewModel: WeatherViewModel, symbol: String) =
    "${viewModel.weatherInfo.value?.lastWeatherInfo?.minTemp.toString().substring(0, 2)}${symbol}"

fun getCurrentTemp(viewModel: WeatherViewModel, symbol: String) =
    "${viewModel.weatherInfo.value?.lastWeatherInfo?.theTemp.toString().substring(0, 2)}${symbol}"

fun getRemoteImage(viewModel: WeatherViewModel) = BuildConfig.BASE_URL_IMAGES.replace(
    "$1", viewModel.weatherInfo.value?.lastWeatherInfo?.weatherStateAbbr.toString()
)


@Preview(showBackground = true)
@Composable
fun WeatherHomePreview() {
    FaireWeatherAppTheme {
        WeatherHomeScreen()
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun WeatherHomePreviewDark() {
    FaireWeatherAppTheme {
        WeatherHomeScreen()
    }
}