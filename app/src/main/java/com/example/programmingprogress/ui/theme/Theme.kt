package com.example.programmingprogress.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val DarkColorPalette = darkColors(
    primary = DarkBlue,
    primaryVariant = Blue,
    secondary = DarkMagenta
)

private val LightColorPalette = lightColors(
    primary = DarkBlue,
    primaryVariant = Blue,
    secondary = DarkMagenta

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun ProgrammingProgressTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
data class CalendarAlertDialogTheme (
    val backgroundColor: Color = Alpha,
    val colorButton: Color = DarkMagenta,
    val width: Dp = 100.dp,
    val calendarTheme: CalendarTheme = CalendarTheme(),
)
data class CalendarTheme(
    val calendarItemTheme: CalendarItemTheme = CalendarItemTheme(),
    val calendarHeaderTheme: CalendarHeaderTheme = CalendarHeaderTheme(),
    val backgroundColor: Color = Alpha,
)

data class CalendarHeaderTheme(
    val headerBackgroundColor: Color = Alpha,
    val headerTextColor: Color = DarkGray,
    val headerTextStyle: TextStyle = TextStyle.Default,
    val headerTextSize: TextUnit = 24.sp,
    val headerTextWeight: FontWeight = FontWeight.Bold
)

data class CalendarItemTheme(
    val textSize: TextUnit = 18.sp,
    val textStyle: TextStyle = TextStyle.Default,
    val dayShape: RoundedCornerShape = CircleShape,
    val dayBackgroundColor: Color = White,
    val elevationDay: Dp = 5.dp,
    val selectedDayBackgroundColor: Color = DarkMagenta,
    val dayValueTextColor: Color = Color.Black,
    val selectedDayValueTextColor: Color = White,
)