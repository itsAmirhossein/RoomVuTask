package com.jahanshahi.roomvu.features.video.info.component
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jahanshahi.roomvu.presentation.video.entity.VideoView
import com.valentinilk.shimmer.Shimmer
import androidx.compose.material3.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.jahanshahi.roomvu.ui.theme.Typography
import com.valentinilk.shimmer.shimmer
@Composable
fun VideoTitleLayout(
    modifier: Modifier = Modifier,
    videoView: VideoView,
    shimmerInstance: Shimmer,
    isLoading: Boolean,
    factor: Float,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape((12*factor).dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding((16*factor).dp),
        ) {
            Text(
                text = videoView.title,
                style = TextStyle(
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = (16*factor).sp,
                    color = Color.Black
                ),
                modifier = Modifier.then(
                    if (isLoading)
                        Modifier.shimmer(shimmerInstance)
                    else
                        Modifier
                ),
            )
            Spacer(modifier = Modifier.height((8*factor).dp))
            Text(
                text = videoView.description,
                style = TextStyle(
                    fontFamily = Typography.bodyMedium.fontFamily,
                    fontSize = (16*factor).sp,
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