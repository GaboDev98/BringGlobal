package com.gabodev.bringglobal.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import com.google.gson.Gson
import okhttp3.OkHttpClient
import dagger.hilt.InstallIn
import javax.inject.Singleton
import android.content.Context
import com.google.gson.GsonBuilder
import java.util.concurrent.TimeUnit
import com.gabodev.bringglobal.BuildConfig
import okhttp3.logging.HttpLoggingInterceptor
import com.gabodev.bringglobal.data.local.CityDao
import retrofit2.converter.gson.GsonConverterFactory
import com.gabodev.bringglobal.data.local.AppDatabase
import com.gabodev.bringglobal.data.remote.CityService
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.components.ApplicationComponent
import com.gabodev.bringglobal.data.repository.CityRepository
import com.gabodev.bringglobal.data.remote.CityRemoteDataSource

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    private val timeOut: Long
        get() = BuildConfig.TIME_OUT_SECONDS

    private val logging
        get() = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BASIC)

    private val okHttpClient
        get() = OkHttpClient.Builder()
            .connectTimeout(timeOut, TimeUnit.SECONDS)
            .readTimeout(timeOut, TimeUnit.SECONDS)
            .addInterceptor(logging)

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient.build())
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideCityService(retrofit: Retrofit): CityService = retrofit.create(CityService::class.java)

    @Singleton
    @Provides
    fun provideCityRemoteDataSource(cityService: CityService) = CityRemoteDataSource(cityService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCityDao(db: AppDatabase) = db.cityDao()

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CityRemoteDataSource,
                          localDataSource: CityDao) =
        CityRepository(remoteDataSource, localDataSource)
}