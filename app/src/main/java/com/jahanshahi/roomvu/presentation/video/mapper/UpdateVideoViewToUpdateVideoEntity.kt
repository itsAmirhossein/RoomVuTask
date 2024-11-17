package com.jahanshahi.roomvu.presentation.video.mapper

import com.jahanshahi.roomvu.common.mapper.Mapper
import com.jahanshahi.roomvu.domain.video.entity.UpdateVideoEntity
import com.jahanshahi.roomvu.presentation.video.entity.UpdateVideoView
import javax.inject.Inject

class UpdateVideoViewToUpdateVideoEntity @Inject constructor() :
    Mapper<UpdateVideoView, UpdateVideoEntity> {
    override fun map(first: UpdateVideoView): UpdateVideoEntity {
        return UpdateVideoEntity(
            title = first.title,
            description = first.description
        )
    }
}