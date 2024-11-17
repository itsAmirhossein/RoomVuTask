package com.amirhossein.friendfinder.presentation.common.component.ripple_effect

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.RippleConfiguration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

val RoomVuRippleAlpha = RippleAlpha(
    draggedAlpha = 0.0f,
    focusedAlpha = 0.0f,
    hoveredAlpha = 0.0f,
    pressedAlpha = 0.0f,
)

@OptIn(ExperimentalMaterial3Api::class)
val NoRippleTheme = RippleConfiguration(color = Color.Red, rippleAlpha = RoomVuRippleAlpha)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RippleEffectHandler(
    hasRippleEffect: Boolean = true,
    content: @Composable () -> Unit,
) {
    if (hasRippleEffect)
        content()
    else
        CompositionLocalProvider(LocalRippleConfiguration provides NoRippleTheme) {
            content()
        }
}