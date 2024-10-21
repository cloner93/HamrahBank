package com.pmb.ballon.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pmb.ballon.R


val appFontFamily = FontFamily(
    Font(R.font.irsans_regular, FontWeight.Normal),
    Font(R.font.irsans_bold, FontWeight.Bold),
)

data class CustomTypography(
    val caption: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
    ),
    val overline: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 13.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.sp,
    ),
    val bodySmall: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight(416),
    ),
    val bodyMedium: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(416),
    ),
    val bodyLarge: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(416),
    ),
    val buttonXSmall: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight(520),
    ),
    val buttonSmall: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight(416),
    ),
    val buttonMedium: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 14.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(416),
    ),
    val buttonLarge: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(520),
    ),
    val subtitleLarge: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight(416),
    ),
    val subtitleMedium: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight(416),
    ),
    val subtitleSmall: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight(416),
    ),
    val displayLarge: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 46.sp,
        lineHeight = 54.sp,
        fontWeight = FontWeight.Bold,
    ),
    val displayMedium: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 40.sp,
        lineHeight = 52.sp,
        fontWeight = FontWeight.Bold,
    ),
    val displaySmall: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 36.sp,
        lineHeight = 47.sp,
        fontWeight = FontWeight.Bold,
    ),
    val headline1: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 34.sp,
        lineHeight = 44.sp,
        fontWeight = FontWeight.Bold,
    ),
    val headline2: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Bold,
    ),
    val headline3: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    val headline4: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 21.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    val headline5: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    val headline6: TextStyle = TextStyle(
        fontFamily = appFontFamily,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold,
    ),
)

val AppTypography = CustomTypography()

// Set of Material typography styles to start with
//val AppTypography = Typography(
//    bodyMedium = TextStyle(
//        fontFamily = appFontFamily,
//        fontWeight = FontWeight.Normal,
//        fontSize = 14.sp,
//        lineHeight = 20.sp
//    ),
//    bodyLarge = TextStyle(
//        fontFamily = appFontFamily,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.5.sp
//    ),
//    displayLarge = TextStyle(
//        fontFamily = appFontFamily,
//        fontWeight = FontWeight.Bold,
//        fontSize = 46.sp,
//        lineHeight = 54.sp,
//        letterSpacing = 0.sp
//    ),
//    displayMedium = TextStyle(
//        fontFamily = appFontFamily,
//        fontWeight = FontWeight.Bold,
//        fontSize = 40.sp,
//        lineHeight = 52.sp,
//        letterSpacing = 0.sp
//    ),
//    displaySmall = TextStyle(
//        fontFamily = appFontFamily,
//        fontWeight = FontWeight.Bold,
//        fontSize = 36.sp,
//        lineHeight = 47.sp,
//        letterSpacing = 0.sp
//    ),
//    headlineLarge = TextStyle(
//        fontFamily = appFontFamily,
//        fontWeight = FontWeight.Bold,
//        fontSize = 34.sp,
//        lineHeight = 44.sp,
//        letterSpacing = 0.sp
//    ),
//    headlineMedium = TextStyle(
//        fontFamily = appFontFamily,
//        fontWeight = FontWeight.Bold,
//        fontSize = 28.sp,
//        lineHeight = 36.sp,
//        letterSpacing = 0.sp
//    ),
//    headlineSmall = TextStyle(
//        fontFamily = appFontFamily,
//        fontWeight = FontWeight.SemiBold,
//        fontSize = 24.sp,
//        lineHeight = 32.sp,
//        letterSpacing = 0.sp
//    ),
//    titleLarge = TextStyle(
//        fontFamily = appFontFamily,
//        fontWeight = FontWeight(520),
//        fontSize = 20.sp,
//        lineHeight = 26.sp,
//        letterSpacing = 0.sp
//    ),
////    titleMedium =,
////    titleSmall =,
////    bodySmall =,
//    labelLarge = TextStyle(
//        fontFamily = appFontFamily,
//        fontWeight = FontWeight(520),
//        fontSize = 16.sp,
//        lineHeight = 24.sp,
//        letterSpacing = 0.sp
//    ),
////    labelMedium =,
////    labelSmall =,
//)