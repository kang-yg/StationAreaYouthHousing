package com.kyg.stationareayouthhousing.model.di

import android.content.Context
import androidx.room.Room
import com.kyg.stationareayouthhousing.Constants
import com.kyg.stationareayouthhousing.model.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "StationAreaYouthHousingDB").addMigrations(MIGRATION_1_2).build()

    @Singleton
    @Provides
    fun provideLocalServiceHelper(localServiceHelperImpl: LocalServiceHelperImpl): LocalServiceHelper = localServiceHelperImpl

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("X-NCP-APIGW-API-KEY-ID", "1tsq2ldc0x")
            .addHeader("X-NCP-APIGW-API-KEY", "bwgmXWRSPVVQnP3YUBclqT3qlL7RLj87BC6PTDy9")
            .build()
        chain.proceed(request)
    }).build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Singleton
    @Provides
    fun provideRemoteService(retrofit: Retrofit) = retrofit.create(RemoteService::class.java)

    @Singleton
    @Provides
    fun provideRemoteServiceHelper(remoteServiceHelperImpl: RemoteServiceHelperImpl): RemoteServiceHelper = remoteServiceHelperImpl
}