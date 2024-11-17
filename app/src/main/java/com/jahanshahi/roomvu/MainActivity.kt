package com.jahanshahi.roomvu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jahanshahi.roomvu.presentation.component.topbar.RoomVuTopBar
import com.jahanshahi.roomvu.navigation.SetupNavigationHost
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarLeftActionButton
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarRightActionButton
import com.jahanshahi.roomvu.presentation.component.topbar.TopBarTitle
import com.jahanshahi.roomvu.ui.theme.RoomVuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController: NavHostController = rememberNavController()
            RoomVuTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        RoomVuTopBar(
                            navController = navController,
                            topBarTitle = TopBarTitle(
                                text = "Video",
                            ), //TODO stringResource(id = R.string.home_title)
                            topBarLeftButton = TopBarLeftActionButton.BACK_BUTTON,
                            onLeftActionButtonClick = {
                                navController.navigateUp()
                            },
                            topBarRightActionButton = TopBarRightActionButton.MORE_ACTION_BUTTON,
                            onRightActionButtonClick = {
                                //TODO
                            }
                        )
                    },
                ) { innerPadding ->
                    SetupNavigationHost(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}