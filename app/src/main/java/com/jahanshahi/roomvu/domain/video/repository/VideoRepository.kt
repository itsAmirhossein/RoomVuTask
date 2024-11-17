package com.sample.domain.faq.repository

import com.jahanshahi.roomvu.domain.video.entity.UpdateVideoEntity
import com.jahanshahi.roomvu.domain.video.entity.VideoEntity
import kotlinx.coroutines.flow.Flow

interface VideoRepository {
    suspend fun getVideo(): Flow<VideoEntity>
    suspend fun updateVideo(updateVideoEntity: UpdateVideoEntity): Flow<List<VideoEntity>>
}