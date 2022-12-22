package com.example.faireweatherapp.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import com.example.faireweatherapp.R
import com.example.faireweatherapp.presentation.ui.theme.backgroundDarkThemeColor
import com.example.faireweatherapp.presentation.ui.theme.backgroundLightThemeColor

@Composable
fun LandingScreen(modifier: Modifier = Modifier) {
    val backgroundColor = if (isSystemInDarkTheme()) backgroundDarkThemeColor else backgroundLightThemeColor
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Brush.linearGradient(backgroundColor))

    ) {
        Image(
            painterResource(id = R.drawable.img_landing_screen),
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}
