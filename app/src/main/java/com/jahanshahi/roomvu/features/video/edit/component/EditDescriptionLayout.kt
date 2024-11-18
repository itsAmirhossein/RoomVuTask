package com.jahanshahi.roomvu.features.video.edit.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jahanshahi.roomvu.ui.theme.Typography

@Composable
fun EditDescriptionLayout(
    modifier: Modifier = Modifier,
    descriptionText: String,
    onDescriptionTextChange: (String) -> Unit
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Edit Content of your Video post",
            modifier = Modifier.padding(start = 16.dp),
            style = TextStyle(
                fontFamily = Typography.bodyMedium.fontFamily,
                fontSize = 14.sp,
                color = Color(0xff8E8E93)
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            TextField(
                value = descriptionText,
                onValueChange = {
                    onDescriptionTextChange(it)
                },
                minLines = 5,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,

                    ),
                textStyle = TextStyle(
                    fontFamily = Typography.bodyMedium.fontFamily,
                    fontSize = 16.sp,
                )
            )
        }
    }
}