package com.jahanshahi.roomvu.features.video.info.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jahanshahi.roomvu.R
import com.jahanshahi.roomvu.presentation.video.entity.VideoView

@Composable
fun SocialMediaIcons(
    modifier: Modifier = Modifier,
    videoView: VideoView,
    factor:Float,
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
                modifier = Modifier.size((24*factor).dp),
                tint = Color.Black
            )
        if (videoView.platforms.twitter)
            Icon(
                painter = painterResource(id = R.drawable.ic_twitter),
                contentDescription = "Twitter Icon",
                modifier = Modifier.size((24*factor).dp),
                tint = Color.Black
            )
        if (videoView.platforms.instagram)
            Icon(
                painter = painterResource(id = R.drawable.ic_insta),
                contentDescription = "Instagram Icon",
                modifier = Modifier.size((24*factor).dp),
                tint = Color.Black
            )
    }
}