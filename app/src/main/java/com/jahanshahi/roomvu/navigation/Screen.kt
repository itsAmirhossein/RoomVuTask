package com.jahanshahi.roomvu.navigation

sealed class Screen(val route : String){
    data object VideoScreen: Screen("video_screen")
    data object VideoEditScreen: Screen("video_edit_screen")
}
