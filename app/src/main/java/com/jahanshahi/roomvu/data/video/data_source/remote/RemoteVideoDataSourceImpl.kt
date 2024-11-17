package com.jahanshahi.roomvu.data.video.data_source.remote

import android.util.Log
import com.jahanshahi.roomvu.data.core.api.bodyOrThrow
import com.jahanshahi.roomvu.data.video.api.remote.entity.request.update_video.UpdateVideoRequestRemote
import com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video.VideoResponseRemote
import com.jahanshahi.roomvu.data.video.api.remote.service.VideoApiRemoteService
import javax.inject.Inject
import kotlin.math.log

class RemoteVideoDataSourceImpl @Inject constructor(
    private val videoApiRemoteService: VideoApiRemoteService,
) : RemoteVideoDataSource {
    override suspend fun getVideo(): VideoResponseRemote {
        val result = videoApiRemoteService.getVideo().bodyOrThrow()
        return result.getData().video
    }

    override suspend fun updateVideo(
        updateVideoRequestRemote: UpdateVideoRequestRemote,
    ): List<VideoResponseRemote> {
        val result = videoApiRemoteService.updateVideo(
            updateVideoRequestRemote = updateVideoRequestRemote
        ).bodyOrThrow()
        return result.getData()
    }
}