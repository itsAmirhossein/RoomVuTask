package com.jahanshahi.roomvu.data.video.di

import com.jahanshahi.roomvu.common.mapper.Mapper
import com.jahanshahi.roomvu.data.video.api.remote.entity.request.update_video.UpdateVideoRequestRemote
import com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video.PlatformsResponseRemote
import com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video.VideoResponseRemote
import com.jahanshahi.roomvu.data.video.api.remote.service.VideoApiRemoteService
import com.jahanshahi.roomvu.data.video.data_source.remote.RemoteVideoDataSource
import com.jahanshahi.roomvu.data.video.data_source.remote.RemoteVideoDataSourceImpl
import com.jahanshahi.roomvu.domain.video.entity.PlatformsEntity
import com.jahanshahi.roomvu.domain.video.entity.UpdateVideoEntity
import com.jahanshahi.roomvu.domain.video.entity.VideoEntity
import com.sample.data.faq.mapper.PlatformsResponseRemoteToPlatformsEntity
import com.sample.data.faq.mapper.UpdateVideoResponseRemoteToUpdateVideoEntity
import com.sample.data.faq.mapper.VideoResponseRemoteToVideoEntity
import com.sample.data.faq.repository.VideoRepositoryImpl
import com.sample.domain.faq.repository.VideoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class) // todo hilt
@Module
abstract class VideoDataModule {
    //mapper
    @Binds
    abstract fun bindVideoResponseRemoteToVideoEntity(mapper: VideoResponseRemoteToVideoEntity): Mapper<VideoResponseRemote, VideoEntity>

    @Binds
    abstract fun bindPlatformsResponseRemoteToPlatformsEntity(mapper: PlatformsResponseRemoteToPlatformsEntity): Mapper<PlatformsResponseRemote, PlatformsEntity>

    @Binds
    abstract fun bindUpdateVideoRequestremoteToUpdateVideoEntity(mapper: UpdateVideoResponseRemoteToUpdateVideoEntity): Mapper<UpdateVideoRequestRemote, UpdateVideoEntity>

    //Data Source
    @Binds
    abstract fun bindVideoDataSource(dataSource: RemoteVideoDataSourceImpl): RemoteVideoDataSource

    //Repo
    @Binds
    abstract fun bindVideoRepository(repo: VideoRepositoryImpl): VideoRepository

    companion object {
        @Provides
        fun provideVideoApiRemoteService(retrofit: Retrofit): VideoApiRemoteService {
            return retrofit.create(VideoApiRemoteService::class.java)
        }
    }
}