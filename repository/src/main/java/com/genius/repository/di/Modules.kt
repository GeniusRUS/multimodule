package com.genius.repository.di

import android.content.Context
import com.genius.core.Core
import com.genius.repository.IFeatureRandomJokeRepository
import com.genius.repository.FeatureRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class FeatureJokeRepositoryModule {

    @Provides
    @Singleton
    fun provideContext(): Context = Core.context

    @Provides
    @Singleton
    fun provideFeatureJokeRepository(api: Api, context: Context): IFeatureRandomJokeRepository {
        return FeatureRepositoryImpl(api, context)
    }

    @Provides
    @Singleton
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(45, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofit(): Api {
        return Retrofit.Builder()
            .baseUrl(Api.SERVER)
            .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setPrettyPrinting().create()))
            .build()
            .create(Api::class.java)
    }
}