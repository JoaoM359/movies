package com.example.movies.utils

import platform.Foundation.NSNumber
import platform.Foundation.NSNumberFormatter

actual fun Double.formatRate(): String {
    val formatter = NSNumberFormatter()
    formatter.minimumFractionDigits = 1u
    formatter.maximumFractionDigits = 1u
    formatter.numberStyle = 1u
    return formatter.stringFromNumber(NSNumber(this)) ?: ""
}