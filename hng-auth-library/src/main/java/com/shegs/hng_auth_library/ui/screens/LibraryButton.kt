package com.shegs.hng_auth_library.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun LibraryButton(onClick: () -> Unit, texts: String) {

    Button(onClick = { onClick() }) {
        Text(text = texts)
    }
}

