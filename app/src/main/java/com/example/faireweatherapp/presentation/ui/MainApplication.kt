package com.example.faireweatherapp.presentation.ui

import android.app.Application
import com.example.faireweatherapp.core.di.AppModule
import com.example.faireweatherapp.core.di.KoinUtilities
import org.koin.core.context.loadKoinModules

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        KoinUtilities.loadKoin(applicationContext)
        loadKoinModules(AppModule.allModules())
    }

}