package com.jahanshahi.roomvu.data.core.module


import com.jahanshahi.roomvu.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit.MINUTES
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton
import kotlin.math.log

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
    }
    @Singleton
    @Provides
    fun provideOkHttp(
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val dispatcher = Dispatcher(Executors.newFixedThreadPool(20))
        dispatcher.maxRequests = 20
        dispatcher.maxRequestsPerHost = 20

        return OkHttpClient.Builder()
            .apply {
                addInterceptor(loggingInterceptor)
                dispatcher(dispatcher)
                connectionPool(ConnectionPool(100, 30L, SECONDS))
                readTimeout(30L, MINUTES)
                writeTimeout(30L, MINUTES)
                connectTimeout(30L, MINUTES)
            }.build()
    }

    @Singleton
    @Provides
    fun provideRoomVuRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .apply {
                baseUrl(BuildConfig.ROOM_VU_BASE_URL)
                client(okHttpClient)
                addConverterFactory(GsonConverterFactory.create())
            }.build()
    }
}
