package com.jahanshahi.roomvu.features.video.info.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.jahanshahi.roomvu.ui.theme.Typography

@Composable
fun RecordPersonalIntroButton(
    modifier: Modifier,
    factor: Float,
    ) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape((8*factor).dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(width = (1*factor).dp, color = Color(0xffD0D5DD))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding((12*factor).dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_record),
                contentDescription = "Record Personal Intro Icon",
                modifier = Modifier.size((24*factor).dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width((8*factor).dp))
            Text(
                text = "Record personal intro",
                style = TextStyle(
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = (14*factor).sp,
                    color = Color.Black
                )
            )
        }
    }
}