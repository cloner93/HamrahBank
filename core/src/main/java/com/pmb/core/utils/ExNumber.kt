package com.pmb.core.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrency(): String {
    val formatter = NumberFormat.getInstance(Locale.US).apply {
        maximumFractionDigits = 0 // Removes decimal points
    }
    return formatter.format(this)
}

fun Int.toTimeFormat(): String {
    val num = this
    return when {
        num >= 3600 -> "%02d:%02d:%02d".format(this / 3600, (this % 3600) / 60, this % 60)
        else -> "%02d:%02d".format(this / 60, this % 60)
    }
}
