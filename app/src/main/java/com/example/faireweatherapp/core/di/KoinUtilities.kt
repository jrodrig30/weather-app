package com.example.faireweatherapp.core.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

object KoinUtilities {
    fun loadKoin(context: Context) {
        GlobalContext.getOrNull() ?: run {
            startKoin {
                androidContext(context)
            }
        }
    }
}