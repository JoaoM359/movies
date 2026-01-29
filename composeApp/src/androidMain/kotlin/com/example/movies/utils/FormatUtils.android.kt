package com.example.movies.utils

import java.util.Locale

actual fun Double.formatRate(): String =
    String.format(Locale.getDefault(), "%.1f", this)
