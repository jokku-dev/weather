package com.jokku.weather.di

import android.content.Context
import androidx.room.Room
import com.jokku.weather.data.source.WeatherDataSource
import com.jokku.weather.data.source.local.WeatherDatabase
import com.jokku.weather.data.source.local.WeatherLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Qualifier
//    @Retention(AnnotationRetention.RUNTIME)
//    annotation class LocalWeatherDataSource

    @Singleton
//    @LocalWeatherDataSource
    @Provides
    fun provideWeatherLocalDataSource(
        database: WeatherDatabase,
        ioDispatcher: CoroutineDispatcher
    ): WeatherDataSource {
        return WeatherLocalDataSource(
            database.weatherDao(),
            ioDispatcher
        )
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "weather.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideIoDispatcher() = Dispatchers.IO

//    @Singleton
//    @Provides
//    fun provideWeatherRepository(
//        localTasksDataSource: WeatherDataSource,
//        ioDispatcher: CoroutineDispatcher
//    ): WeatherRepository {
//    }
}