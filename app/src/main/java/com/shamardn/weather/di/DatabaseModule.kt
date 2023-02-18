package com.shamardn.weather.di

import android.content.Context
import com.shamardn.weather.data.local.DataStorePreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataStorePreferences(@ApplicationContext context: Context) =
        DataStorePreferences(context)

}