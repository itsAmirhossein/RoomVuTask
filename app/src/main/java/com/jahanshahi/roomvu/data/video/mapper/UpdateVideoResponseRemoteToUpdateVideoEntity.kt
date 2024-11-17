package com.sample.data.faq.mapper

import com.jahanshahi.roomvu.common.mapper.Mapper
import com.jahanshahi.roomvu.data.video.api.remote.entity.request.update_video.UpdateVideoRequestRemote
import com.jahanshahi.roomvu.domain.video.entity.UpdateVideoEntity
import javax.inject.Inject

class UpdateVideoResponseRemoteToUpdateVideoEntity @Inject constructor() :
    Mapper<UpdateVideoRequestRemote, UpdateVideoEntity> {
    override fun map(first: UpdateVideoRequestRemote): UpdateVideoEntity {
        return UpdateVideoEntity(
            title = first.title,
            description = first.description,
        )
    }
}