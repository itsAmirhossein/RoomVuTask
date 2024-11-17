package com.jahanshahi.roomvu.data.core.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UtilsModule {
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}