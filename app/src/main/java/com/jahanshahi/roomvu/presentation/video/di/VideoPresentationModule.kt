package com.sample.presentation.faq.di

import com.jahanshahi.roomvu.common.mapper.Mapper
import com.jahanshahi.roomvu.domain.video.entity.PlatformsEntity
import com.jahanshahi.roomvu.domain.video.entity.UpdateVideoEntity
import com.jahanshahi.roomvu.domain.video.entity.VideoEntity
import com.jahanshahi.roomvu.presentation.video.entity.PlatformsView
import com.jahanshahi.roomvu.presentation.video.entity.UpdateVideoView
import com.jahanshahi.roomvu.presentation.video.entity.VideoView
import com.jahanshahi.roomvu.presentation.video.mapper.PlatformsEntityToPlatformsView
import com.jahanshahi.roomvu.presentation.video.mapper.UpdateVideoViewToUpdateVideoEntity
import com.jahanshahi.roomvu.presentation.video.mapper.VideoEntityToVideoView
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class) // todo hilt
@Module
abstract class FaqPresentationModule {
    //mapper
    @Binds
    abstract fun bindVideoEntityToVideoView(mapper: VideoEntityToVideoView): Mapper<VideoEntity, VideoView>

    @Binds
    abstract fun bindPlatformsEntityToPlatformsView(mapper: PlatformsEntityToPlatformsView): Mapper<PlatformsEntity, PlatformsView>

    @Binds
    abstract fun bindUpdateVideoViewToUpdateVideoEntity(mapper: UpdateVideoViewToUpdateVideoEntity): Mapper<UpdateVideoView, UpdateVideoEntity>
}