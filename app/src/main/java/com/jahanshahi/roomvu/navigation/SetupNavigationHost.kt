package com.jahanshahi.roomvu.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jahanshahi.roomvu.features.video.edit.screen.VideoEditScreen
import com.jahanshahi.roomvu.features.video.info.screen.VideoInfoScreen

@Composable
fun SetupNavigationHost(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.VideoInfoScreen.route
    ) {
        composable(route = Screen.VideoInfoScreen.route) {
            VideoInfoScreen(
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(route = Screen.VideoEditScreen.route,
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) {backStackEntry->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val description = backStackEntry.arguments?.getString("description") ?: ""
            VideoEditScreen(
                navController = navController,
                initialTitle = title,
                initialDescription = description,
            )
        }
    }
}