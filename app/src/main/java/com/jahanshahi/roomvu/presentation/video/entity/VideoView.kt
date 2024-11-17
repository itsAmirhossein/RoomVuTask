package com.jahanshahi.roomvu.presentation.video.entity

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField

data class VideoView(
    val videoUrl: String,
    val thumbnail: String,
    val title: String,
    val description: String,
    val publishAt: String,
    val platforms: PlatformsView,
) {
    companion object {
        val DEFAULT_VALUE = VideoView(
            videoUrl = "",
            thumbnail = "",
            title = "",
            description = "",
            publishAt = "1999-12-04 12:00:00",
            platforms = PlatformsView.DEFAULT_VALUE,
        )
    }
}
