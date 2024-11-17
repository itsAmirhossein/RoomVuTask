package com.jahanshahi.roomvu.presentation.video.edit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jahanshahi.roomvu.presentation.component.topbar.RoomVuTopBar
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarLeftActionButton
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarRightActionButton
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarTitle
import com.jahanshahi.roomvu.ui.theme.Typography

@Composable
fun VideoEditScreen(
    navController: NavController,
) {
    Scaffold(
        containerColor = Color(0xfff3f1f7),
        modifier = Modifier.fillMaxSize(),
        topBar = {
            RoomVuTopBar(
                topBarTitle = TopBarTitle(
                    text = "Edit",
                ),
                topBarLeftActionButton = TopBarLeftActionButton.CANCEL_BUTTON,
                onLeftActionButtonClick = {
                    navController.navigateUp()
                },
                topBarRightActionButton = TopBarRightActionButton.SAVE_ACTION_BUTTON,
                onRightActionButtonClick = {
                    //TODO
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            EditTitleLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            EditContentLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
fun EditTitleLayout(modifier: Modifier = Modifier) {
    var titleText by remember {
        mutableStateOf("Title")
    }
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "Edit title of your  Video post",
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
                containerColor = Color.White,
            ),
            shape = RoundedCornerShape(4.dp)
        ) {
            TextField(
                value = titleText,
                onValueChange = {
                    titleText = it
                },
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
                    fontFamily = Typography.bodyLarge.fontFamily,
                    fontSize = 16.sp,
                ),
            )
        }
    }
}

@Composable
fun EditContentLayout(modifier: Modifier = Modifier) {
    var contentText by remember {
        mutableStateOf("#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag#hashtag")
    }
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
                value = contentText,
                onValueChange = {
                    contentText = it
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

