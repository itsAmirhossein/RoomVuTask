package com.jahanshahi.roomvu.presentation.video.entity

import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.jvm.javaField

data class UpdateVideoView(
    val title: String,
    val description: String,
)
