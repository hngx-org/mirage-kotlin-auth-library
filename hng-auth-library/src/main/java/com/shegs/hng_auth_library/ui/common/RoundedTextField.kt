package com.shegs.hng_auth_library.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.shegs.hng_auth_library.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoundedTextField(
    value: String,
    keyboardActions: KeyboardActions? = null,
    keyboardOptions: KeyboardOptions? = null,
    placeHolderText: String,
    visualTransformation: VisualTransformation? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier.fillMaxWidth(),
    onValueChange: (String) -> Unit
) {

    val focsManager = LocalFocusManager.current

    OutlinedTextField(
        value = value,
        visualTransformation = visualTransformation!!,
        onValueChange = { onValueChange(it) },
        modifier = modifier,
        keyboardOptions = keyboardOptions!!,
        keyboardActions = keyboardActions!!,
        placeholder = {
            Text(
                text = placeHolderText,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter_light)),
                    fontWeight = FontWeight(200),
                    color = Color(0xFF000000),
                )
            )
        },
//        leadingIcon = {
//            Icon(imageVector = icon, contentDescription = "")
//        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        trailingIcon = trailingIcon,
        keyboardActions = KeyboardActions(
            onNext = {
                focsManager.moveFocus(FocusDirection.Next)
            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            containerColor = Color.White,
            unfocusedIndicatorColor = Color.Black,
            disabledIndicatorColor = Color.Black
        ),
        shape = MaterialTheme.shapes.medium,
        maxLines = 1,
        singleLine = true,
    )
}

//@Preview
//@Composable
//fun RoundedTextFieldPreview() {
//
//    Surface(
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            RoundedTextField(
//                value = "",
//                placeHolderText = "Firstname and lastname",
//                onValueChange = {}
//            )
//        }
//    }
//}
