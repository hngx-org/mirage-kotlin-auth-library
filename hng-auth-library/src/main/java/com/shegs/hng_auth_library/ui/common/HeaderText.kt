package com.shegs.hng_auth_library.ui.common

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.shegs.hng_auth_library.R

@Composable
fun HeaderText(text: String) {
    Text(
        text = text, style = TextStyle(
            fontSize = 24.sp,
            fontFamily = FontFamily(Font(R.font.inter_bold)),
            fontWeight = FontWeight(700),
            color = Color(0xFF000000),
        )
    )
}