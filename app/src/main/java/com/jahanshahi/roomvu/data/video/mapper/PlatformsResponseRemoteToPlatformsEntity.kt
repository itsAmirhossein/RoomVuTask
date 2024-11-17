package com.sample.data.faq.mapper

import com.jahanshahi.roomvu.common.mapper.Mapper
import com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video.PlatformsResponseRemote
import com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video.VideoResponseRemote
import com.jahanshahi.roomvu.domain.video.entity.PlatformsEntity
import com.jahanshahi.roomvu.domain.video.entity.VideoEntity
import javax.inject.Inject
import kotlin.random.Random

class PlatformsResponseRemoteToPlatformsEntity @Inject constructor() :
    Mapper<PlatformsResponseRemote, PlatformsEntity> {
    override fun map(first: PlatformsResponseRemote): PlatformsEntity {
        return PlatformsEntity(
            instagram = first.instagram,
            twitter = first.twitter,
            linkedin = first.linkedin,
        )
    }
}