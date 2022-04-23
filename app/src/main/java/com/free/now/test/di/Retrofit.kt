package com.free.now.test.di

import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


private const val CONNECTION_TIMEOUT = 30L
private const val WRITE_TIMEOUT = 30L
private const val READ_TIMEOUT = 30L
private const val BASE_URL = "https://fake-poi-api.mytaxi.com/"

val retrofitModule = module {
    single { Cache(androidApplication().cacheDir, 10L * 1024 * 1024) }
    single { GsonBuilder().create() }
    single { retrofitHttpClient() }
    single { retrofitBuilder() }
    single {
        Interceptor { chain ->
            chain.proceed(
                chain.request().newBuilder().apply {
                    addHeader("Accept", "application/json")
                }.build()
            )
        }
    }
}

private fun Scope.retrofitHttpClient(): OkHttpClient =
    OkHttpClient.Builder().apply {
        cache(get())
        connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        retryOnConnectionFailure(true)
    }.build()

private fun Scope.retrofitBuilder(): Retrofit =
    Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(get()))
        .client(get())
        .build()
