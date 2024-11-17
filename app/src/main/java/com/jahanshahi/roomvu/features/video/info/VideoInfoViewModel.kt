package com.jahanshahi.roomvu.features.video.info

import android.content.Context
import androidx.annotation.OptIn
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.util.UnstableApi
import com.jahanshahi.roomvu.presentation.video.entity.VideoView
import com.jahanshahi.roomvu.presentation.video.mapper.VideoEntityToVideoView
import com.sample.domain.core.entity.Resource
import com.sample.domain.core.entity.flowMap
import com.sample.domain.faq.useCase.GetVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoInfoViewModel @Inject constructor(
    private val getVideoUseCase: GetVideoUseCase,
    private val videoEntityToVideoView: VideoEntityToVideoView,
) : ViewModel() {

    private val _video = MutableStateFlow<Resource<VideoView>>((Resource.Loading(true)))
    val video: StateFlow<Resource<VideoView>>
        get() = _video

    init {
        getVideoFromRemote()
    }

    private fun getVideoFromRemote() {
        viewModelScope.launch {
            getVideoUseCase(Unit).flowMap(
                _video,
                videoEntityToVideoView
            )
        }
    }
}