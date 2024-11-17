package com.sample.data.core.webApi.response

import com.google.gson.annotations.SerializedName


data class BaseResponse<T>(
    @SerializedName("status") var status: String,
    @SerializedName("message") var message: String? = null,
    @SerializedName("data") private var data: T,
) {
    fun getData() = data
}