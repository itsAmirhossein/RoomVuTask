package com.jahanshahi.roomvu.features.common.component.topbar

import androidx.annotation.DrawableRes

sealed class IconContent {
    data class ImageIcon(@DrawableRes val imageIcon: Int) : IconContent()
    data class TextIcon(val text : String) : IconContent()
}