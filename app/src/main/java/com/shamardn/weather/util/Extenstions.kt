package com.shamardn.weather.util

import java.util.*

fun Long.formatDate(pattern: String): String{
    val simpleDateFormat = java.text.SimpleDateFormat(pattern)
    val date = Date(this * 1000)
    return simpleDateFormat.format(date)
}
