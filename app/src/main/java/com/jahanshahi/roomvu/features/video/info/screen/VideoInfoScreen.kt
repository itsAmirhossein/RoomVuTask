package com.jahanshahi.roomvu.features.video.info.screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jahanshahi.roomvu.features.video.info.component.VideoItem
import com.jahanshahi.roomvu.features.common.component.topbar.RoomVuTopBar
import com.jahanshahi.roomvu.features.common.component.topbar.TopBarLeftActionButton
import com.jahanshahi.roomvu.features.common.component.topbar.TopBarRightActionButton
import com.jahanshahi.roomvu.features.common.component.topbar.TopBarTitle
import com.jahanshahi.roomvu.presentation.video.entity.VideoView
import com.sample.domain.core.entity.onAnyError
import com.sample.domain.core.entity.onLoading
import com.sample.domain.core.entity.onSuccess
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import kotlinx.coroutines.flow.collectLatest


@Composable
fun VideoInfoScreen(
    modifier: Modifier = Modifier,
    viewModel: VideoInfoViewModel = hiltViewModel(),
    navController: NavController,
) {
    var isPopUpMenuExpanded by remember { mutableStateOf(false) }
    var video by remember {
        mutableStateOf(VideoView.DEFAULT_VALUE)
    }
    val videoState = viewModel.video
    val shimmerInstance = rememberShimmer(
        shimmerBounds = ShimmerBounds.Window,
        theme = defaultShimmerTheme.copy(
            shaderColors = listOf(
                Color.Unspecified.copy(alpha = 0.20f),
                Color.Unspecified.copy(alpha = 0.60f),
                Color.Unspecified.copy(alpha = 0.20f),
            ),
            blendMode = BlendMode.Src,
            rotation = 45.0f
        )
    )
    var isLoading by remember {
        mutableStateOf(true)
    }
    val configuration = LocalConfiguration.current
    val factor = if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        1f else 1.61f
    LaunchedEffect(videoState) {
        videoState.collectLatest {
            it.run {
                onSuccess { videoView ->
                    video = videoView
                }
                onAnyError { _, s ->
                    Log.e(
                        "Error",
                        "Video Info Screen -> video state error: ${s.orEmpty()}"
                    )
                }
                onLoading {
                    isLoading = it
                }
            }
        }
    }
    Scaffold(
        containerColor = Color(0xfff3f1f7),
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            RoomVuTopBar(
                navController = navController,
                topBarTitle = TopBarTitle(
                    text = "Video",
                ),
                topBarLeftActionButton = TopBarLeftActionButton.BACK_BUTTON,
                onLeftActionButtonClick = {
                    navController.navigateUp()
                },
                topBarRightActionButton = TopBarRightActionButton.MORE_ACTION_BUTTON,
                onRightActionButtonClick = {
                    isPopUpMenuExpanded = true
                },
                isPopUpMenuExpanded = isPopUpMenuExpanded,
                onIsPopUpMenuExpandedChange = {
                    isPopUpMenuExpanded = it
                },
                title = video.title,
                description = video.description,
                factor = factor,
            )
        },
    ) { innerPadding ->
        VideoItem(
            modifier = modifier.then(Modifier.padding(innerPadding)),
            videoView = video,
            shimmerInstance = shimmerInstance,
            isLoading = isLoading,
            factor = factor,
        )
    }
}