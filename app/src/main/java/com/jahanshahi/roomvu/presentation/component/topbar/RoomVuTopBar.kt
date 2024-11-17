package com.jahanshahi.roomvu.presentation.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amirhossein.friendfinder.presentation.common.component.ripple_effect.RippleEffectHandler
import com.jahanshahi.roomvu.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomVuTopBar(
    modifier: Modifier = Modifier,
    topBarTitle: TopBarTitle,
    topBarLeftActionButton: TopBarLeftActionButton,
    onLeftActionButtonClick: () -> Unit,
    topBarRightActionButton: TopBarRightActionButton = TopBarRightActionButton.MORE_ACTION_BUTTON,
    onRightActionButtonClick: () -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .drawBehind {
            val borderThickness = 1.dp.toPx() // Bottom border thickness
            drawLine(
                color = Color.Gray,
                start = androidx.compose.ui.geometry.Offset(
                    0f,
                    size.height
                ), // Starting at the bottom-left
                end = androidx.compose.ui.geometry.Offset(
                    size.width,
                    size.height
                ), // Ending at the bottom-right
                strokeWidth = borderThickness // Line thickness
            )
        }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(color = Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RippleEffectHandler(
                hasRippleEffect = false
            ) {
                Box(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable {
                            onLeftActionButtonClick()
                        }
                ) {
                    when (topBarLeftActionButton.iconContent) {
                        is IconContent.ImageIcon -> {
                            Icon(
                                painter = painterResource(id = (topBarLeftActionButton.iconContent).imageIcon),
                                contentDescription = topBarLeftActionButton.contentDescription,
                                tint = topBarLeftActionButton.color,
                                modifier = Modifier
                                    .size(topBarLeftActionButton.size)
                            )
                        }

                        is IconContent.TextIcon -> {
                            Text(
                                text = (topBarLeftActionButton.iconContent).text,
                                style = TextStyle(
                                    fontFamily = Typography.bodyMedium.fontFamily,
                                    fontSize = 16.sp,
                                    color = Color(0xff007AFF)
                                ),
                            )
                        }
                    }
                }
            }
            Text(
                text = topBarTitle.text,
                style = TextStyle(
                    fontFamily = topBarTitle.fontFamily,
                    fontSize = topBarTitle.fontSize,
                    color = topBarTitle.color,
                )
            )
            RippleEffectHandler(
                hasRippleEffect = false
            ) {
                Box(
                    modifier = Modifier.padding(end = 12.dp).clickable {
                        onRightActionButtonClick()
                    }
                ) {
                    when (topBarRightActionButton.iconContent) {
                        is IconContent.ImageIcon -> {
                            Icon(
                                painter = painterResource(id = (topBarRightActionButton.iconContent).imageIcon),
                                contentDescription = topBarRightActionButton.contentDescription,
                                tint = topBarRightActionButton.color,
                                modifier = Modifier.size(topBarRightActionButton.size)
                            )
                        }

                        is IconContent.TextIcon -> {
                            Text(
                                text = (topBarRightActionButton.iconContent).text,
                                style = TextStyle(
                                    fontFamily = Typography.bodyMedium.fontFamily,
                                    fontSize = 16.sp,
                                    color = Color(0xff007AFF)
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}