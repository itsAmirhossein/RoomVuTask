package com.jahanshahi.roomvu.navigation

import com.jahanshahi.roomvu.presentation.component.topbar.TopBarTitle

sealed class Screen(val route : String){
    data object VideoInfoScreen: Screen("video_info_screen")
    data object VideoEditScreen: Screen("video_edit_screen/{title}/{description}")
}
