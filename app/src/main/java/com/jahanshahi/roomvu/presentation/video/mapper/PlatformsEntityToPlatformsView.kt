package com.jahanshahi.roomvu.presentation.video.mapper

import com.jahanshahi.roomvu.common.mapper.Mapper
import com.jahanshahi.roomvu.domain.video.entity.PlatformsEntity
import com.jahanshahi.roomvu.presentation.video.entity.PlatformsView
import javax.inject.Inject

class PlatformsEntityToPlatformsView @Inject constructor() :
    Mapper<PlatformsEntity, PlatformsView> {
    override fun map(first: PlatformsEntity): PlatformsView {
        return PlatformsView(
            instagram = first.instagram,
            twitter = first.twitter,
            linkedin = first.linkedin,
        )

    }
}