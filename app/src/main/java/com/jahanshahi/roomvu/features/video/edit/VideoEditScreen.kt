package com.jahanshahi.roomvu.features.video.edit

import android.util.Log
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jahanshahi.roomvu.domain.video.entity.VideoEntity
import com.jahanshahi.roomvu.presentation.component.topbar.RoomVuTopBar
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarLeftActionButton
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarRightActionButton
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarTitle
import com.jahanshahi.roomvu.presentation.video.entity.UpdateVideoView
import com.jahanshahi.roomvu.presentation.video.entity.VideoView
import com.jahanshahi.roomvu.ui.theme.Typography
import com.sample.domain.core.entity.onAnyError
import com.sample.domain.core.entity.onLoading
import com.sample.domain.core.entity.onSuccess
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VideoEditScreen(
    viewModel: VideoEditViewModel = hiltViewModel(),
    navController: NavController,
    initialTitle: String,
    initialDescription:String,
) {
    val context = LocalContext.current
    var title by remember {
        mutableStateOf(initialTitle)
    }
    var description by remember {
        mutableStateOf(initialDescription)
    }
    val video = viewModel.video
    LaunchedEffect(video) {
        video.collectLatest {
            it.run {
                onSuccess { _ ->
                    Toast.makeText(context, "Video updated!", Toast.LENGTH_SHORT).show()
                    navController.navigateUp()
                }
                onAnyError { _, s ->
                    Log.e(
                        "Error",
                        "Video Edit Screen -> video state error: ${s.orEmpty()}"
                    )
                }
                onLoading {}
            }
        }
    }
    Scaffold(
        containerColor = Color(0xfff3f1f7),
        modifier = Modifier.fillMaxSize(),
        topBar = {
            RoomVuTopBar(
                navController = navController,
                topBarTitle = TopBarTitle(
                    text = "Edit",
                ),
                topBarLeftActionButton = TopBarLeftActionButton.CANCEL_BUTTON,
                onLeftActionButtonClick = {
                    navController.navigateUp()
                },
                topBarRightActionButton = TopBarRightActionButton.SAVE_ACTION_BUTTON,
                onRightActionButtonClick = {
                    viewModel.updateVideo(
                        UpdateVideoView(
                            title = title,
                            description = title,
                        )
                    )
                },
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
                    .padding(16.dp),
                titleText = title,
                onTitleTextChanged = {
                    title = it
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            EditDescriptionLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                descriptionText = description,
                onDescriptionTextChange = {
                    description = it
                }
            )
        }
    }
}

@Composable
fun EditTitleLayout(modifier: Modifier = Modifier,titleText:String,onTitleTextChanged:(String)->Unit) {
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
                    onTitleTextChanged(it)
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
fun EditDescriptionLayout(modifier: Modifier = Modifier,descriptionText:String,onDescriptionTextChange:(String)->Unit) {
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

