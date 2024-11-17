package com.jahanshahi.roomvu.data.video.api.remote.service

import com.jahanshahi.roomvu.data.video.api.remote.entity.request.update_video.UpdateVideoRequestRemote
import com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video.GetVideoResponseRemote
import com.jahanshahi.roomvu.data.video.api.remote.entity.response.get_video.VideoResponseRemote
import com.sample.data.core.webApi.response.BaseResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface VideoApiRemoteService {
    @GET("android-challenge/video")
    suspend fun getVideo(
    ): Response<BaseResponse<GetVideoResponseRemote>>

    @PUT("android-challenge/video")
    fun updateVideo(
        @Body updateVideoRequestRemote: UpdateVideoRequestRemote,
    ): Response<BaseResponse<List<VideoResponseRemote>>>

}