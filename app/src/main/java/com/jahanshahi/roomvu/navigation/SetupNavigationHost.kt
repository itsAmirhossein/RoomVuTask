package com.jahanshahi.roomvu.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jahanshahi.roomvu.presentation.video.edit.VideoEditScreen
import com.jahanshahi.roomvu.presentation.video.info.VideoInfoScreen

@Composable
fun SetupNavigationHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.VideoScreen.route
    ) {
        composable(route = Screen.VideoScreen.route) {
            VideoInfoScreen(
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Screen.VideoEditScreen.route) {
            VideoEditScreen(
                navController = navController,
            )
        }
    }
}