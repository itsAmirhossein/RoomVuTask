package com.jahanshahi.roomvu.features.video.info.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jahanshahi.roomvu.R

@Composable
fun VideoPlayIcon(
    onClick: () -> Unit,
    factor:Float,
) {
    Box(
        modifier = Modifier
            .size((36*factor).dp)
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
            modifier = Modifier.size((16*factor).dp),
            tint = Color(0xff2979FF)
        )
    }
}