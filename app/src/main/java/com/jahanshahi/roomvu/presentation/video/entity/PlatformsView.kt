package com.jahanshahi.roomvu.presentation.video.entity

data class PlatformsView(
    val instagram: Boolean,
    val twitter: Boolean,
    val linkedin: Boolean,
){
    companion object{
        val DEFAULT_VALUE = PlatformsView(
            instagram = false,
            twitter = false,
            linkedin = false
        )
    }
}