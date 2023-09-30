package com.shegs.hng_auth_library.common

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
fun TextFieldHeaderText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.inter_regular)),
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
        )
    )
}