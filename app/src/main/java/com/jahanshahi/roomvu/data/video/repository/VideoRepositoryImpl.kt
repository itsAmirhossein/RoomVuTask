package com.sample.data.faq.repository

import com.jahanshahi.roomvu.data.video.data_source.remote.RemoteVideoDataSource
import com.jahanshahi.roomvu.domain.video.entity.UpdateVideoEntity
import com.jahanshahi.roomvu.domain.video.entity.VideoEntity
import com.sample.data.faq.mapper.UpdateVideoResponseRemoteToUpdateVideoEntity
import com.sample.data.faq.mapper.VideoResponseRemoteToVideoEntity
import com.sample.domain.faq.repository.VideoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(
    private val remoteVideoDataSource: RemoteVideoDataSource,
    private val videoResponseRemoteToVideoEntity: VideoResponseRemoteToVideoEntity,
    private val updateVideoResponseRemoteToUpdateVideoEntity: UpdateVideoResponseRemoteToUpdateVideoEntity,
) : VideoRepository {
    override suspend fun getVideo(): Flow<VideoEntity> {
        return flow {
            emit(
                videoResponseRemoteToVideoEntity.map(remoteVideoDataSource.getVideo())
            )
        }
    }

    override suspend fun updateVideo(updateVideoEntity: UpdateVideoEntity): Flow<List<VideoEntity>> {
        return flow {
            emit(
                listOf()
            )
        }
    }

}