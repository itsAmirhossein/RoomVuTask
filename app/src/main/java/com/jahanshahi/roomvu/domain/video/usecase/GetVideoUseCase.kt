package com.sample.domain.faq.useCase

import android.util.Log
import com.jahanshahi.roomvu.common.thread.IoDispatcher
import com.jahanshahi.roomvu.domain.video.entity.VideoEntity
import com.sample.domain.core.entity.Resource
import com.jahanshahi.roomvu.domain.core.usecase.FlowUseCase
import com.sample.domain.faq.repository.VideoRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

class GetVideoUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val videoRepository: VideoRepository,
) : FlowUseCase<Unit, VideoEntity>(dispatcher) {
    override fun execute(parameters: Unit): Flow<Resource<VideoEntity>> {
        return channelFlow {
            send(Resource.Loading(true))
            videoRepository.getVideo().collectLatest {
                send(Resource.Success(it))
            }
        }
    }
}