package com.example.movies.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import movies.composeapp.generated.resources.Res
import movies.composeapp.generated.resources.Urbanist_Bold
import movies.composeapp.generated.resources.Urbanist_Medium
import movies.composeapp.generated.resources.Urbanist_Regular
import org.jetbrains.compose.resources.Font

val urbanist: FontFamily
    @Composable get() = FontFamily(
        Font(resource = Res.font.Urbanist_Regular, weight = FontWeight.Normal),
        Font(resource = Res.font.Urbanist_Medium, weight = FontWeight.Medium),
        Font(resource = Res.font.Urbanist_Bold, weight = FontWeight.Bold)
    )

@Composable
fun appTypography() = Typography(
    displaySmall = TextStyle(
        fontSize = 26.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = urbanist
    ),
    headlineLarge = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = urbanist,
    ),
    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = urbanist,
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = urbanist,
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = urbanist,
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = urbanist,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = urbanist,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = urbanist,
    )
)