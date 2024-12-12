package com.jpmobilelab.kmp.weatherapp.di

import com.jpmobilelab.kmp.weatherapp.core.data.HttpClientFactory
import com.jpmobilelab.kmp.weatherapp.weather.data.remote.KtorRemoteWeatherDataSource
import com.jpmobilelab.kmp.weatherapp.weather.data.remote.RemoteWeatherDataSource
import com.jpmobilelab.kmp.weatherapp.weather.domain.repository.DefaultWeatherRepository
import com.jpmobilelab.kmp.weatherapp.weather.domain.repository.WeatherRepository
import com.jpmobilelab.kmp.weatherapp.weather.ui.WeatherViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }

    singleOf(::KtorRemoteWeatherDataSource).bind<RemoteWeatherDataSource>()
    singleOf(::DefaultWeatherRepository).bind<WeatherRepository>()

    viewModelOf(::WeatherViewModel)
}
