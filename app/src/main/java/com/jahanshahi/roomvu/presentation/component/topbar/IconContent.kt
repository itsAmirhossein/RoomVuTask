package com.jahanshahi.roomvu.presentation.component.topbar

import androidx.annotation.DrawableRes

sealed class IconContent {
    data class ImageIcon(@DrawableRes val imageIcon: Int) : IconContent()
    data class TextIcon(val text : String) : IconContent()
}