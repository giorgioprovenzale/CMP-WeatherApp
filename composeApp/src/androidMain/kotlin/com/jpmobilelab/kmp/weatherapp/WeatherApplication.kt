package com.jpmobilelab.kmp.weatherapp

import android.app.Application
import com.jpmobilelab.kmp.weatherapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@WeatherApplication)
        }
    }
}