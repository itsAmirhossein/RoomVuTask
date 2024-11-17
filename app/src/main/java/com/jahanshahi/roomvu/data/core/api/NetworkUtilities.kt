package com.jahanshahi.roomvu.data.core.api

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sample.data.core.webApi.response.BaseResponse
import retrofit2.HttpException
import retrofit2.Response

fun <T> Response<T>.bodyOrThrow(): T {
    if (!isSuccessful) {
        val value = this.errorBody()?.string()
        if ((value.isNullOrEmpty()).not()) {
            throw Exception(value)
        } else {
            throw HttpException(this)
        }
    }
    return body()!!
}