package com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video

import com.google.gson.annotations.SerializedName

data class GetVideoResponseRemote(
    @SerializedName("video") val video: VideoResponseRemote,
)