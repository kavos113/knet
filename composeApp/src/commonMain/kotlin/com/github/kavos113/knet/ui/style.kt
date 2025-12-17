package com.github.kavos113.knet.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

object CommonColor {
    val background = Color(0xfffbfbfb)
    val backgroundDark = Color(0xffebebeb)
    val foreground = Color(0xff414141)
    val mainDark = Color(0xff00b400)
    val mainLight = Color(0xff91e451)
    val folder = Color(0xffe4ac10)
    val gray = Color(0xffc9c9c9)
    val isNotSaved = Color(0xff0048ff)
}

object CommonStyle {
    val normalTextStyle = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight(400),
        color = CommonColor.foreground
    )
}