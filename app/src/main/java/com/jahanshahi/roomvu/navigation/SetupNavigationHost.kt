package com.jahanshahi.roomvu.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.jahanshahi.roomvu.presentation.video.VideoInfoScreen

@Composable
fun SetupNavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.VideoScreen.route
    ) {
        composable(route = Screen.VideoScreen.route) {
            VideoInfoScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
        //Bottom Navigation
        composable(route = Screen.VideoEditScreen.route) {
            //TODO
        }
    }
}