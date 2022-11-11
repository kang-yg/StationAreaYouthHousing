package com.kyg.stationareayouthhousing.model.di

import android.content.Context
import androidx.room.Room
import com.kyg.stationareayouthhousing.model.AppDataBase
import com.kyg.stationareayouthhousing.model.LocalServiceHelper
import com.kyg.stationareayouthhousing.model.LocalServiceHelperImpl
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "StationAreaYouthHousingDB").build()

    @Singleton
    @Provides
    fun provideLocalServiceHelper(localServiceHelperImpl: LocalServiceHelperImpl): LocalServiceHelper = localServiceHelperImpl
}