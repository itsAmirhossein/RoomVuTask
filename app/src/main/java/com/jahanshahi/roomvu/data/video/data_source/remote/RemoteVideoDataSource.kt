package com.jahanshahi.roomvu.data.video.data_source.remote

import com.jahanshahi.roomvu.data.video.api.remote.entity.request.update_video.UpdateVideoRequestRemote
import com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video.VideoResponseRemote

interface RemoteVideoDataSource {
    suspend fun getVideo(): VideoResponseRemote
    suspend fun updateVideo(updateVideoRequestRemote: UpdateVideoRequestRemote): List<VideoResponseRemote>
}