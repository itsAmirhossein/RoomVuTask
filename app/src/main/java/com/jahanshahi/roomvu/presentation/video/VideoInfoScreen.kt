package com.jahanshahi.roomvu.presentation.video

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jahanshahi.roomvu.R
import com.jahanshahi.roomvu.ui.theme.Typography

@Composable
fun VideoInfoScreen(modifier: Modifier = Modifier) {
    VideoItem(modifier = modifier)
}

@Composable
fun VideoItem(modifier: Modifier = Modifier) {
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
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(4.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fake),
                    contentDescription = "Video Thumbnail",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                VideoPlayIcon()
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        VideoInfoLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Spacer(modifier = Modifier.height(24.dp))
        VideoTitleLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        RecordPersonalIntroButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        )
    }
}

@Composable
fun VideoPlayIcon() {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.8f),
            ),
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
fun VideoInfoLayout(modifier: Modifier = Modifier) {
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
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Tips for First-Time Homebuyers in Chicago",
                style = TextStyle(
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = 14.sp,
                    color = Color.Black
                )
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
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "09:09 PM",
                style = TextStyle(
                    fontFamily = Typography.bodyMedium.fontFamily,
                    fontSize = 12.sp,
                    color = Color.Black
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            TagChip {
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
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
            SocialMediaIcons()
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
fun SocialMediaIcons(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_linkedin),
            contentDescription = "LinkedIn Icon",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_twitter),
            contentDescription = "Twitter Icon",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
        Icon(
            painter = painterResource(id = R.drawable.ic_insta),
            contentDescription = "Instagram Icon",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}

@Composable
fun VideoTitleLayout(modifier: Modifier = Modifier) {
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
                text = "Title",
                style = TextStyle(
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag",
                style = TextStyle(
                    fontFamily = Typography.bodyMedium.fontFamily,
                    fontSize = 16.sp,
                    color = Color.Black
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
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
