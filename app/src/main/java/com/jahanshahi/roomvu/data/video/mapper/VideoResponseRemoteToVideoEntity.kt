package com.sample.data.faq.mapper

import com.jahanshahi.roomvu.common.mapper.Mapper
import com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video.VideoResponseRemote
import com.jahanshahi.roomvu.domain.video.entity.VideoEntity
import javax.inject.Inject
import kotlin.random.Random

class VideoResponseRemoteToVideoEntity @Inject constructor(
    private val platformsResponseRemoteToPlatformsEntity: PlatformsResponseRemoteToPlatformsEntity,
) :
    Mapper<VideoResponseRemote, VideoEntity> {
    override fun map(first: VideoResponseRemote): VideoEntity {
        return VideoEntity(
            videoUrl = first.videoUrl,
            thumbnail = first.thumbnail,
            title = first.title,
            description = first.description,
            publishAt = first.publishAt,
            platforms = platformsResponseRemoteToPlatformsEntity.map(first.platforms),
        )
    }
}