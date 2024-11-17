package com.jahanshahi.roomvu.presentation.video.mapper

import com.jahanshahi.roomvu.common.mapper.Mapper
import com.jahanshahi.roomvu.domain.video.entity.VideoEntity
import com.jahanshahi.roomvu.presentation.video.entity.VideoView
import javax.inject.Inject

class VideoEntityToVideoView @Inject constructor(
    private val platformsEntityToPlatformsView: PlatformsEntityToPlatformsView,
) : Mapper<VideoEntity, VideoView> {
    override fun map(first: VideoEntity): VideoView {
        return VideoView(
            videoUrl = first.getVideoUrl(),
            thumbnail = first.getThumbnail(),
            title = first.getTitle(),
            description = first.getDescription(),
            publishAt = first.getPublishAt(),
            platforms = platformsEntityToPlatformsView.map(first.getPlatforms()),
        )
    }
}