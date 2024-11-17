package com.jahanshahi.roomvu.features.video.info

import android.net.Uri
import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.jahanshahi.roomvu.R
import com.jahanshahi.roomvu.core.extensions.getTime
import com.jahanshahi.roomvu.presentation.component.topbar.RoomVuTopBar
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarLeftActionButton
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarRightActionButton
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarTitle
import com.jahanshahi.roomvu.presentation.video.entity.VideoView
import com.jahanshahi.roomvu.ui.theme.Typography
import com.sample.domain.core.entity.onAnyError
import com.sample.domain.core.entity.onLoading
import com.sample.domain.core.entity.onSuccess
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
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
            rotation = 30.0f
        )
    )
    var isLoading by remember {
        mutableStateOf(true)
    }
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
                title=video.title,
                description = video.description,
            )
        },
    ) { innerPadding ->
        VideoItem(
            modifier = modifier.then(Modifier.padding(innerPadding)),
            videoView = video,
            shimmerInstance = shimmerInstance,
            isLoading = isLoading,
        )
    }
}

@Composable
fun VideoItem(
    modifier: Modifier = Modifier,
    videoView: VideoView,
    shimmerInstance: Shimmer,
    isLoading: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Jan 21",
            style = TextStyle(
                fontFamily = Typography.bodyLarge.fontFamily,
                fontSize = 15.sp,
            ),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp)
                .then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
            shape = RoundedCornerShape(4.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                VideoContainer(
                    modifier = Modifier.fillMaxSize(),
                    videoUrl = videoView.videoUrl,
                    videoThumbnail = videoView.thumbnail,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        VideoInfoLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            videoView = videoView,
            shimmerInstance = shimmerInstance,
            isLoading = isLoading,
        )
        Spacer(modifier = Modifier.height(24.dp))
        VideoTitleLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            videoView = videoView,
            shimmerInstance = shimmerInstance,
            isLoading = isLoading,
        )
        Spacer(modifier = Modifier.height(16.dp))
        RecordPersonalIntroButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
        )
    }
}

@OptIn(UnstableApi::class)
@Composable
fun VideoContainer(
    modifier: Modifier = Modifier,
    videoUrl: String,
    videoThumbnail: String,
) {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    var exoplayer by remember { mutableStateOf<ExoPlayer?>(null) }
    val playerListener = object : Player.Listener {
        override fun onPlaybackStateChanged(playbackState: Int) {
            when (playbackState) {
                Player.STATE_IDLE -> Log.d("ExoPlayer", "Player is idle")
                Player.STATE_BUFFERING -> Log.d("ExoPlayer", "Player is buffering")
                Player.STATE_READY -> Log.d("ExoPlayer", "Player is ready to play")
                Player.STATE_ENDED -> {
                    Log.d("ExoPlayer", "Playback ended")
                    exoplayer?.apply {
                        playWhenReady = false
                        release()
                    }
                    exoplayer = null
                    isPlaying = false
                }
            }
        }

        override fun onPlayerError(error: PlaybackException) {
            Log.e("ExoPlayer", "Player error: ${error.message}")
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            Log.d("ExoPlayer", if (isPlaying) "Playing" else "Paused")
        }
    }
    // When the player is initialized
    LaunchedEffect(key1 = isPlaying) {
        if (isPlaying && exoplayer == null) {
            val mediaItem = MediaItem.fromUri(Uri.parse(videoUrl))
            exoplayer = ExoPlayer.Builder(context).build().apply {
                setMediaItem(mediaItem)
                addListener(playerListener)
                playWhenReady = true
                prepare()
            }
        }
    }
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (isPlaying && exoplayer != null) {
            // Show ExoPlayer when video is playing
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoplayer
                        useController = true
                        setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING)
                        this.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                    }
                },
                modifier = modifier,
            )
        } else {
            // Display Thumbnail Image with play button
            AsyncImage(
                model = videoThumbnail,
                contentDescription = "Video Thumbnail",
                modifier = modifier,
                contentScale = ContentScale.Crop
            )
            VideoPlayIcon(
                onClick = {
                    isPlaying = true
                }
            )
        }
    }

    // Cleanup ExoPlayer when no longer needed
    DisposableEffect(key1 = isPlaying) {
        onDispose {
            exoplayer?.apply {
                playWhenReady = false
                release()
            }
            exoplayer = null
        }
    }
}

@Composable
fun VideoPlayIcon(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.8f),
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_play),
            contentDescription = "Play Icon",
            modifier = Modifier.size(16.dp),
            tint = Color(0xff2979FF)
        )
    }
}

@Composable
fun VideoInfoLayout(
    modifier: Modifier = Modifier,
    videoView: VideoView,
    shimmerInstance: Shimmer,
    isLoading: Boolean,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_video),
                contentDescription = "Video Info Icon",
                modifier = Modifier
                    .size(20.dp)
                    .then(
                        if (isLoading)
                            Modifier.shimmer(shimmerInstance)
                        else
                            Modifier
                    ),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = videoView.title,
                style = TextStyle(
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = 14.sp,
                    color = Color.Black
                ),
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_time),
                contentDescription = "Time Icon",
                modifier = Modifier
                    .size(20.dp)
                    .then(
                        if (isLoading)
                            Modifier.shimmer(shimmerInstance)
                        else
                            Modifier
                    ),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = videoView.publishAt.getTime(),
                style = TextStyle(
                    fontFamily = Typography.bodyMedium.fontFamily,
                    fontSize = 12.sp,
                    color = Color.Black
                ),
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
            )
            Spacer(modifier = Modifier.width(4.dp))
            TagChip(
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_tag),
                    contentDescription = "Tag Icon",
                    modifier = Modifier.size(12.dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "A.I. Picked",
                    style = TextStyle(
                        fontFamily = Typography.bodyLarge.fontFamily,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "This video will be posted on",
                style = TextStyle(
                    fontFamily = Typography.bodyMedium.fontFamily,
                    fontSize = 12.sp,
                    color = Color.Black
                ),
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
            )
            Spacer(modifier = Modifier.width(12.dp))
            SocialMediaIcons(
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
                videoView = videoView,
            )
        }
    }
}

@Composable
fun TagChip(modifier: Modifier = Modifier, content: @Composable RowScope.() -> Unit) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(100.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF7C67E5)
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp),
        ) {
            content()
        }
    }
}

@Composable
fun SocialMediaIcons(
    modifier: Modifier = Modifier,
    videoView: VideoView,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (videoView.platforms.linkedin)
            Icon(
                painter = painterResource(id = R.drawable.ic_linkedin),
                contentDescription = "LinkedIn Icon",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        if (videoView.platforms.twitter)
            Icon(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = "Twitter Icon",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        if (videoView.platforms.instagram)
            Icon(
                painter = painterResource(id = R.drawable.ic_insta),
                contentDescription = "Instagram Icon",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
    }
}

@Composable
fun VideoTitleLayout(
    modifier: Modifier = Modifier,
    videoView: VideoView,
    shimmerInstance: Shimmer,
    isLoading: Boolean,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = videoView.title,
                style = TextStyle(
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = videoView.description,
                style = TextStyle(
                    fontFamily = Typography.bodyMedium.fontFamily,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .then(
                        if (isLoading)
                            Modifier.shimmer(shimmerInstance)
                        else
                            Modifier
                    ),
                textAlign = TextAlign.Start,
            )
        }
    }
}

@Composable
fun RecordPersonalIntroButton(modifier: Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(width = 1.dp, color = Color(0xffD0D5DD))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_record),
                contentDescription = "Record Personal Intro Icon",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Record personal intro",
                style = TextStyle(
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = 14.sp,
                    color = Color.Black
                )
            )
        }
    }
}
