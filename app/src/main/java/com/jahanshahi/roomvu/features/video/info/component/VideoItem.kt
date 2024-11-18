package com.jahanshahi.roomvu.features.video.info.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jahanshahi.roomvu.presentation.video.entity.VideoView
import com.jahanshahi.roomvu.ui.theme.Typography
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun VideoItem(
    modifier: Modifier = Modifier,
    videoView: VideoView,
    shimmerInstance: Shimmer,
    isLoading: Boolean,
    factor: Float,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
        }
        item {
            Text(
                text = "Jan 21",
                style = TextStyle(
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = (15*factor).sp,
                ),
                modifier = Modifier
                    .padding(horizontal = (16*factor).dp)
                    .then(
                        if (isLoading)
                            Modifier.shimmer(shimmerInstance)
                        else
                            Modifier
                    ),
            )
        }
        item {
            Spacer(modifier = Modifier.height((12*factor).dp))
        }
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height((200*factor).dp)
                    .padding(horizontal = (16*factor).dp)
                    .then(
                        if (isLoading)
                            Modifier.shimmer(shimmerInstance)
                        else
                            Modifier
                    ),
                shape = RoundedCornerShape((4*factor).dp),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,
                ) {
                    VideoContainer(
                        modifier = Modifier.fillMaxSize(),
                        videoUrl = videoView.videoUrl,
                        videoThumbnail = videoView.thumbnail,
                        factor = factor,
                    )
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height((8*factor).dp))
        }
        item {
            VideoInfoLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (16*factor).dp),
                videoView = videoView,
                shimmerInstance = shimmerInstance,
                isLoading = isLoading,
                factor = factor,
            )
        }
        item {
            Spacer(modifier = Modifier.height((24*factor).dp))
        }
        item {
            VideoTitleLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (16*factor).dp),
                videoView = videoView,
                shimmerInstance = shimmerInstance,
                isLoading = isLoading,
                factor = factor,
            )
        }
        item {
            Spacer(modifier = Modifier.height((16*factor).dp))
        }
        item{
            RecordPersonalIntroButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = (16*factor).dp)
                    .then(
                        if (isLoading)
                            Modifier.shimmer(shimmerInstance)
                        else
                            Modifier
                    ),
                factor = factor,
            )
        }
        item {
            Spacer(modifier = Modifier.height((12*factor).dp))
        }
    }
}