package com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video

import com.google.gson.annotations.SerializedName

data class PlatformsResponseRemote(
    @SerializedName("instagram") val instagram: Boolean = false,
    @SerializedName("twitter") val twitter: Boolean = false,
    @SerializedName("linkedin") val linkedin: Boolean = false,
)