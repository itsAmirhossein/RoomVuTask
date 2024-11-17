package com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video

import com.google.gson.annotations.SerializedName

data class VideoResponseRemote(
    @SerializedName("video_url") val videoUrl: String? = null,
    @SerializedName("thumbnail") val thumbnail: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("publish_at") val publishAt: String? = null,
    @SerializedName("platforms") val platforms: PlatformsResponseRemote,
)