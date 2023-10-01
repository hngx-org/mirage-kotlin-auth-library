@file:OptIn(ExperimentalMaterial3Api::class)

package com.shegs.hng_auth_library.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shegs.hng_auth_library.R
import com.shegs.hng_auth_library.common.HeaderText
import com.shegs.hng_auth_library.common.LibraryButton
import com.shegs.hng_auth_library.common.RoundedTextField
import com.shegs.hng_auth_library.common.SubtitleText
import com.shegs.hng_auth_library.common.TextFieldHeaderText


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpScreen() {
    var fullName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")

    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    Scaffold(
    ) {
        val focusManager = LocalFocusManager.current
        LazyColumn(
            modifier = Modifier.padding(start = 34.dp, end = 34.dp)
        ) {
            item {
                Spacer(modifier = Modifier.padding(vertical = 50.dp))
            }
            item {
                HeaderText(text = "Let’s get you \nstarted")
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
            }

            item {
                SubtitleText(text = "Your journey begins here. This will \nonly take a few minutes.")
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 40.dp))
            }
            item {
                TextFieldHeaderText(text = "Full Name")
                RoundedTextField(
                    value = fullName,
                    visualTransformation = null,
                    keyboardActions = null,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Text
                    ),
                    placeHolderText = "FirstName and LastName",
                    onValueChange = {fullName = it})
                Spacer(modifier = Modifier.padding(vertical = 10.dp))

            }
            item {
                TextFieldHeaderText(text = "Email")
                RoundedTextField(
                    value = email,
                    visualTransformation = null,
                    keyboardActions = null,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                    placeHolderText = "Email Address",
                    onValueChange = {email = it})
                Spacer(modifier = Modifier.padding(vertical = 10.dp))

            }
            item {
                var passwordVisibility by remember {
                    mutableStateOf(false)
                }

                TextFieldHeaderText(text = "Password")
                RoundedTextField(
                    trailingIcon = {
                        val icon =
                            if (passwordVisibility) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff

                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Next)
                        }
                    ),
                    value = password,
                    placeHolderText = "Enter Password",
                    onValueChange = {password = it})
                Spacer(modifier = Modifier.padding(vertical = 10.dp))

            }
            item {
                var passwordVisibility by remember {
                    mutableStateOf(false)
                }
                TextFieldHeaderText(text = "Confirm Password")
                RoundedTextField(
                    value = confirmPassword,
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    trailingIcon = {
                        val icon =
                            if (passwordVisibility) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff

                        IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    ),
                    placeHolderText = "Enter Password Again",
                    onValueChange = {confirmPassword = it})
                Spacer(modifier = Modifier.padding(vertical = 10.dp))

            }

            item {
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
            }
            item {
                LibraryButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    buttonColors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    text = "Register",
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    ),
                    shape = RoundedCornerShape(size = 10.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
            }
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "Have an account?",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.inter_regular)),
                                fontWeight = FontWeight(200),
                                color = Color(0xFF000000),
                            )
                        )
                        Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                        Text(
                            text = "Login",
                            modifier = Modifier.clickable {  },
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontFamily = FontFamily(Font(R.font.inter_medium)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFF000000),
                            )
                        )
                    }
                }
            }



        }
    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen()
}