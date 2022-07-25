package com.jokku.weather.di

import android.content.Context
import androidx.room.Room
import com.jokku.weather.data.local.WeatherDao
import com.jokku.weather.data.local.WeatherDatabase
import com.jokku.weather.data.repo.DefaultWeatherRepository
import com.jokku.weather.data.repo.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(database: WeatherDatabase): WeatherRepository {
        return DefaultWeatherRepository(
            database.weatherDao()
        )
    }

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            "weather"
        ).fallbackToDestructiveMigration().build()
    }

}