package com.jpmobilelab.kmp.weatherapp.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.jpmobilelab.kmp.weatherapp.data.core.HttpClientFactory
import com.jpmobilelab.kmp.weatherapp.data.database.Database
import com.jpmobilelab.kmp.weatherapp.data.database.DatabaseFactory
import com.jpmobilelab.kmp.weatherapp.data.remote.search.KtorRemoteSearchDataSource
import com.jpmobilelab.kmp.weatherapp.data.remote.search.RemoteSearchDataSource
import com.jpmobilelab.kmp.weatherapp.data.remote.weather.KtorRemoteWeatherDataSource
import com.jpmobilelab.kmp.weatherapp.data.remote.weather.RemoteWeatherDataSource
import com.jpmobilelab.kmp.weatherapp.domain.repository.DefaultLocationRepository
import com.jpmobilelab.kmp.weatherapp.domain.repository.DefaultWeatherRepository
import com.jpmobilelab.kmp.weatherapp.domain.repository.LocationRepository
import com.jpmobilelab.kmp.weatherapp.domain.repository.WeatherRepository
import com.jpmobilelab.kmp.weatherapp.ui.search.SearchViewModel
import com.jpmobilelab.kmp.weatherapp.ui.weather.WeatherViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }

    singleOf(::KtorRemoteWeatherDataSource).bind<RemoteWeatherDataSource>()
    singleOf(::KtorRemoteSearchDataSource).bind<RemoteSearchDataSource>()

    singleOf(::DefaultWeatherRepository).bind<WeatherRepository>()
    singleOf(::DefaultLocationRepository).bind<LocationRepository>()

    single {
        get<DatabaseFactory>().create().setDriver(BundledSQLiteDriver()).build()
    }
    single { get<Database>().locationDao }

    viewModelOf(::WeatherViewModel)
    viewModelOf(::SearchViewModel)
}
