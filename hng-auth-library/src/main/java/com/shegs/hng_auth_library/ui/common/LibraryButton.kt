package com.shegs.hng_auth_library.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LibraryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors,
    shape: Shape,
    textStyle: TextStyle? = TextStyle(),
    text: String
) {

    Button(colors = buttonColors, shape = shape, onClick = { onClick() }, modifier = modifier) {
        Text(text = text, style = textStyle!!)
    }
}

@Preview
@Composable
fun PreviewLibraryButton() {
    LibraryButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 10.dp),
        buttonColors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
        ),
        text = "hii",
    )
}