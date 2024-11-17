package com.jahanshahi.roomvu.domain.video.entity

data class VideoEntity(
    private val videoUrl: String?,
    private val thumbnail: String?,
    private val title: String?,
    private val description: String?,
    private val publishAt: String?,
    private val platforms: PlatformsEntity?,
) {
    fun getVideoUrl() = videoUrl.orEmpty()
    fun getThumbnail() = thumbnail.orEmpty()
    fun getTitle() = title.orEmpty()
    fun getDescription() = description.orEmpty()
    fun getPublishAt() = publishAt.orEmpty()
    fun getPlatforms() = platforms ?: PlatformsEntity(
        instagram = false,
        twitter = false,
        linkedin = false
    )
}