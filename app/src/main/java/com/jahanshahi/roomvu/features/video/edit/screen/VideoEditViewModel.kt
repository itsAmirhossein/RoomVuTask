package com.jahanshahi.roomvu.features.video.edit.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jahanshahi.roomvu.presentation.video.entity.UpdateVideoView
import com.jahanshahi.roomvu.presentation.video.entity.VideoView
import com.jahanshahi.roomvu.presentation.video.mapper.UpdateVideoViewToUpdateVideoEntity
import com.sample.domain.core.entity.Resource
import com.sample.domain.faq.useCase.UpdateVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoEditViewModel @Inject constructor(
    private val updateVideoUseCase: UpdateVideoUseCase,
    private val updateVideoViewToUpdateVideoEntity: UpdateVideoViewToUpdateVideoEntity,
) : ViewModel() {

    private val _video = MutableStateFlow<Resource<List<VideoView>>>((Resource.Loading(true)))
    val video: StateFlow<Resource<List<VideoView>>>
        get() = _video


    fun updateVideo(updateVideoView: UpdateVideoView) {
        viewModelScope.launch {
            updateVideoUseCase(updateVideoViewToUpdateVideoEntity.map(updateVideoView)).collectLatest {
                _video.value = Resource.Success(data = listOf())
            }
        }
    }
}