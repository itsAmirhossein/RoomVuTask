package com.jahanshahi.roomvu.core.extensions

import android.annotation.SuppressLint
import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
fun String.getTime(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val outputFormatter = DateTimeFormatter.ofPattern("hh:mm")
    val dateTime = LocalDateTime.parse(this, inputFormatter)
    return dateTime.format(outputFormatter)
}