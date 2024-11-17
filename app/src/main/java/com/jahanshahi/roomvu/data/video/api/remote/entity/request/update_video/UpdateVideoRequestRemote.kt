package com.jahanshahi.roomvu.data.video.api.remote.entity.request.update_video

import com.google.gson.annotations.SerializedName

data class UpdateVideoRequestRemote(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
)