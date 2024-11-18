package com.jahanshahi.roomvu.features.video.info.component

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun VideoContainer(
    modifier: Modifier = Modifier,
    videoUrl: String,
    videoThumbnail: String,
    factor: Float,
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
                },
                factor = factor,
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