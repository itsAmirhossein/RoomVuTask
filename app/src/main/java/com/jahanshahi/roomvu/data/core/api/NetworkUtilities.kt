package com.jahanshahi.roomvu.data.core.api

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