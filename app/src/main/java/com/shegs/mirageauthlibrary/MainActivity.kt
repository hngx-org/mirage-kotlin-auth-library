package com.shegs.mirageauthlibrary

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.shegs.hng_auth_library.authlibrary.AuthLibrary
import com.shegs.hng_auth_library.model.LoginRequest
import com.shegs.hng_auth_library.model.SignupRequest
import com.shegs.hng_auth_library.network.ApiResponse
import com.shegs.mirageauthlibrary.ui.theme.MirageAuthLibraryTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MirageAuthLibraryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

val authService = AuthLibrary.createAuthService()


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(modifier: Modifier = Modifier) {

    val signupRepository = AuthLibrary.createSignupRepository(authService)


    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm_password by remember { mutableStateOf("") }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = name,
                label = {
                    Text(text = "Name")
                },
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { /* Focus on the next field */ }
                )
            )

            TextField(
                value = email,
                label = {
                        Text(text = "Email")
                },
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { /* Focus on the next field */ }
                )
            )

            TextField(
                value = password,
                label = {
                    Text(text = "Password")
                },
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { /* Focus on the next field */ }
                )
            )

            TextField(
                value = confirm_password,
                label = {
                    Text(text = "Confirm password")
                },
                onValueChange = { confirm_password = it },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = androidx.compose.ui.text.input.ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { /* Focus on the next field */ }
                )
            )

            Button(
                onClick = {
                    // This code will be executed when the button is clicked
                    coroutineScope.launch {
                        val result = signupRepository.signup(
                            SignupRequest(
                                name = name,
                                email = email,
                                password = password,
                                confirm_password = confirm_password
                            )
                        )
                        when (result) {
                            is ApiResponse.Success -> {
                                // Handle successful signup
                                val user = result.data
                                Toast.makeText(context, "Signup successful: ${user.data.name}", Toast.LENGTH_SHORT).show()
                            }

                            is ApiResponse.Error -> {
                                // Handle signup error
                                val errorMessage = result.message
                                // Display error message to the user
                                Toast.makeText(context, "Signup failed: $errorMessage", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Sign Up")
            }


            //SignInScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(){

    val context = LocalContext.current
    val dataStoreRepository = AuthLibrary.createDataStoreRepository(context)
    val loginRepository = AuthLibrary.createLoginRepository(authService, dataStoreRepository)

    val coroutineScope = rememberCoroutineScope()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            label = {
                Text(text = "Email")
            },
            onValueChange = { email = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = androidx.compose.ui.text.input.ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { /* Focus on the next field */ }
            )
        )

        TextField(
            value = password,
            label = {
                Text(text = "Password")
            },
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = androidx.compose.ui.text.input.ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { /* Focus on the next field */ }
            )
        )

        Button(
            onClick = {
                // This code will be executed when the button is clicked
                coroutineScope.launch {
                    val result = loginRepository.login(
                        LoginRequest(
                            email = email,
                            password = password
                        )
                    )
                    when (result) {
                        is ApiResponse.Success -> {
                            // Handle successful signup
                            val user = result.data
                            Toast.makeText(context, "Login successful: ${user.data.email}", Toast.LENGTH_SHORT).show()
                        }

                        is ApiResponse.Error -> {
                            // Handle signup error
                            val errorMessage = result.message
                            // Display error message to the user
                            Toast.makeText(context, "Login failed: $errorMessage", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Sign Up")
        }
    }


}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MirageAuthLibraryTheme {
//        Greeting("Androidd")
//    }
//}