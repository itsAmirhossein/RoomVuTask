package com.jahanshahi.roomvu.core.extensions

import android.annotation.SuppressLint
import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
fun String.getTime(): String {
    // Define the input format
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    // Define the output format
    val outputFormatter = DateTimeFormatter.ofPattern("hh:mm") // 12-hour format with AM/PM

    // Parse the input string to a LocalDateTime object
    val dateTime = LocalDateTime.parse(this, inputFormatter)

    // Format it to the desired output
    return dateTime.format(outputFormatter)
}