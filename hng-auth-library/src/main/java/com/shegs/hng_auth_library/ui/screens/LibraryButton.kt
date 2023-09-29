package com.shegs.hng_auth_library.ui.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun LibraryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    buttonColors: ButtonColors,
    texts: String
) {

    Button(colors = buttonColors, onClick = { onClick() }, modifier = modifier) {
        Text(text = texts)
    }
}

@Preview
@Composable
fun PreviewLibraryButton() {
    LibraryButton(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        buttonColors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
        ),
        texts = "hii"
    )
}