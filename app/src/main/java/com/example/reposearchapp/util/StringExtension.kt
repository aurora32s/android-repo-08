package com.example.reposearchapp.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(
    dateFormat: String = "yyyy-MM-dd t HH:mm:ss z",
): Date {
    val dateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
    val date = dateFormat.parse(this)
    Log.d("Extension", date.toString())
    return date
}
