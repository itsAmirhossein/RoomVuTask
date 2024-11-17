package com.jahanshahi.roomvu.presentation.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amirhossein.friendfinder.presentation.common.component.ripple_effect.RippleEffectHandler
import com.jahanshahi.roomvu.navigation.Screen
import com.jahanshahi.roomvu.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoomVuTopBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    topBarTitle: TopBarTitle,
    topBarLeftActionButton: TopBarLeftActionButton,
    onLeftActionButtonClick: () -> Unit,
    topBarRightActionButton: TopBarRightActionButton = TopBarRightActionButton.MORE_ACTION_BUTTON,
    onRightActionButtonClick: () -> Unit,
    isPopUpMenuExpanded: Boolean = false,
    onIsPopUpMenuExpandedChange: (Boolean) -> Unit = {},
    title: String = "",
    description: String = "",
) {
    var isShowDeleteDialog by remember { mutableStateOf(false) }
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
        if (isShowDeleteDialog) {
            AlertDialog(
                containerColor = Color.White,
                onDismissRequest = {
                    isShowDeleteDialog = false
                }, // Close dialog if clicked outside
                title = {
                    Text(
                        text = "Confirm Deletion",
                        style = TextStyle(
                            fontFamily = Typography.bodyLarge.fontFamily,
                            fontSize = 20.sp,
                        ),
                    )
                },
                text = {
                    Text(
                        text = "Are you sure you want to delete this video?",
                        style = TextStyle(
                            fontFamily = Typography.bodyMedium.fontFamily,
                            fontSize = 16.sp,
                        ),
                    )
                },
                confirmButton = {
                    Text(
                        text = "Delete",
                        modifier = Modifier.clickable {
                            isShowDeleteDialog = false
                        },
                        style = TextStyle(
                            fontFamily = Typography.bodyLarge.fontFamily,
                            fontSize = 16.sp,
                            color = Color.Red,
                        )
                    )
                },
                dismissButton = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Cancel",
                            modifier = Modifier.clickable {
                                isShowDeleteDialog = false
                            },
                            style = TextStyle(
                                fontFamily = Typography.bodyLarge.fontFamily,
                                fontSize = 16.sp,
                                color = Color.Black,
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            )
        }
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
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable {
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
                            VideoInfoPopUpMenu(
                                navController = navController,
                                isPopUpMenuExpanded = isPopUpMenuExpanded,
                                onIsPopUpMenuExpandedChange = onIsPopUpMenuExpandedChange,
                                onIsShowDeleteDialogChange = {
                                    isShowDeleteDialog = true
                                },
                                title = title,
                                description = description,
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

@Composable
fun VideoInfoPopUpMenu(
    navController: NavController,
    isPopUpMenuExpanded: Boolean,
    onIsPopUpMenuExpandedChange: (Boolean) -> Unit,
    onIsShowDeleteDialogChange: (Boolean) -> Unit,
    title: String,
    description: String,
) {
    DropdownMenu(
        containerColor = Color.White,
        expanded = isPopUpMenuExpanded, // Control whether the menu is open
        onDismissRequest = {
            onIsPopUpMenuExpandedChange(false)
        } // Close the menu when clicked outside
    ) {
        DropdownMenuItem(
            text = {
                Text(
                    text = "Edit",
                    style = TextStyle(
                        fontFamily = Typography.bodyLarge.fontFamily,
                        fontSize = 14.sp,
                        color = Color.Black,
                    ),
                )
            },
            onClick = {
                onIsPopUpMenuExpandedChange(false)
                navController.navigate(Screen.VideoEditScreen.route.replace("{title",title).replace("{description}",description))
            })
        DropdownMenuItem(
            text = {
                Text(
                    text = "Connect more social media",
                    style = TextStyle(
                        fontFamily = Typography.bodyLarge.fontFamily,
                        fontSize = 14.sp,
                        color = Color.Black,
                    ),
                )
            },
            onClick = {
                onIsPopUpMenuExpandedChange(false)
            })
        DropdownMenuItem(
            text = {
                Text(
                    text = "Delete",
                    style = TextStyle(
                        fontFamily = Typography.bodyLarge.fontFamily,
                        fontSize = 14.sp,
                        color = Color.Black,
                    ),
                )
            },
            onClick = {
                onIsPopUpMenuExpandedChange(false)
                onIsShowDeleteDialogChange(true)
            })
    }
}
