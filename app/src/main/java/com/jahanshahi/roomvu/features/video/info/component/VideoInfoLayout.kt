package com.jahanshahi.roomvu.features.video.info.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jahanshahi.roomvu.R
import com.jahanshahi.roomvu.core.extensions.getTime
import com.jahanshahi.roomvu.presentation.video.entity.VideoView
import com.jahanshahi.roomvu.ui.theme.Typography
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun VideoInfoLayout(
    modifier: Modifier = Modifier,
    videoView: VideoView,
    shimmerInstance: Shimmer,
    isLoading: Boolean,
    factor: Float,
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
                    .size((20 * factor).dp)
                    .then(
                        if (isLoading)
                            Modifier.shimmer(shimmerInstance)
                        else
                            Modifier
                    ),
            )
            Spacer(modifier = Modifier.width((4 * factor).dp))
            Text(
                text = videoView.title,
                style = TextStyle(
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = (14 * factor).sp,
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
        Spacer(modifier = Modifier.height((8 * factor).dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_time),
                contentDescription = "Time Icon",
                modifier = Modifier
                    .size((20 * factor).dp)
                    .then(
                        if (isLoading)
                            Modifier.shimmer(shimmerInstance)
                        else
                            Modifier
                    ),
            )
            Spacer(modifier = Modifier.width((4 * factor).dp))
            Text(
                text = videoView.publishAt.getTime(),
                style = TextStyle(
                    fontFamily = Typography.bodyMedium.fontFamily,
                    fontSize = (12 * factor).sp,
                    color = Color.Black
                ),
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
            )
            Spacer(modifier = Modifier.width((4 * factor).dp))
            TagChip(
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
                factor = factor,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_tag),
                    contentDescription = "Tag Icon",
                    modifier = Modifier.size((12 * factor).dp),
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width((8 * factor).dp))
                Text(
                    text = "A.I. Picked",
                    style = TextStyle(
                        fontFamily = Typography.bodyLarge.fontFamily,
                        fontSize = (12 * factor).sp,
                        color = Color.White
                    )
                )
            }
        }
        Spacer(modifier = Modifier.height((8 * factor).dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "This video will be posted on",
                style = TextStyle(
                    fontFamily = Typography.bodyMedium.fontFamily,
                    fontSize = (12 * factor).sp,
                    color = Color.Black
                ),
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
            )
            Spacer(modifier = Modifier.width((12 * factor).dp))
            SocialMediaIcons(
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
                videoView = videoView,
                factor = factor,
            )
        }
    }
}