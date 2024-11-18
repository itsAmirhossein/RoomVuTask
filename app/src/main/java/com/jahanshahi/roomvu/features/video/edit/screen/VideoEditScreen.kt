package com.jahanshahi.roomvu.features.video.edit.screen

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jahanshahi.roomvu.R
import com.jahanshahi.roomvu.features.video.edit.component.EditDescriptionLayout
import com.jahanshahi.roomvu.features.video.edit.component.EditTitleLayout
import com.jahanshahi.roomvu.features.common.component.topbar.RoomVuTopBar
import com.jahanshahi.roomvu.features.common.component.topbar.TopBarLeftActionButton
import com.jahanshahi.roomvu.features.common.component.topbar.TopBarRightActionButton
import com.jahanshahi.roomvu.features.common.component.topbar.TopBarTitle
import com.jahanshahi.roomvu.presentation.video.entity.UpdateVideoView
import com.sample.domain.core.entity.onAnyError
import com.sample.domain.core.entity.onLoading
import com.sample.domain.core.entity.onSuccess
import kotlinx.coroutines.flow.collectLatest

@Composable
fun VideoEditScreen(
    viewModel: VideoEditViewModel = hiltViewModel(),
    navController: NavController,
    initialTitle: String,
    initialDescription: String,
) {
    val context = LocalContext.current
    var title by remember {
        mutableStateOf(initialTitle)
    }
    var description by remember {
        mutableStateOf(initialDescription)
    }
    val configuration = LocalConfiguration.current
    val factor = if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        1f else 1.61f
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
                    text = stringResource(id = R.string.edit_screen_title),
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
                factor = factor,
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

