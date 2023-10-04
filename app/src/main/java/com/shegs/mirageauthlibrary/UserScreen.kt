package com.shegs.mirageauthlibrary

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.shegs.hng_auth_library.authlibrary.AuthLibrary
import com.shegs.hng_auth_library.model.AuthResponse
import com.shegs.hng_auth_library.model.LoginRequest
import com.shegs.hng_auth_library.network.ApiResponse
import com.shegs.hng_auth_library.network.RetrofitClient
import kotlinx.coroutines.launch


@Composable
fun UserDataScreen() {
    var userData by remember { mutableStateOf<AuthResponse?>(null) }
    var loading by remember { mutableStateOf(true) }
    var spinning by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()



    val authService = AuthLibrary.createAuthService()
    val dataStoreRepository = AuthLibrary.createDataStoreRepository(context)
    val loginRepository = AuthLibrary.createLoginRepository(authService, dataStoreRepository)

    var cookies: String = "";

    suspend fun fetchProfile() {
        loading = true
        val profileRepository = AuthLibrary.createProfileRepository(authService)

        when (val result = profileRepository.profile()) {
            is ApiResponse.Success -> {
                Log.d("API Response", "Profile API Response: ${result.data}")
                userData = result.data
                loading = false
                Toast.makeText(context, "Fetch Profile success", Toast.LENGTH_SHORT).show()

                // Print credit value from the response
                Log.d("Credit", "Credit value: ${result.data.data.credits}")

                Log.d("Cookies", "Cookies value: $cookies")
                cookies = RetrofitClient.getCookiesForUrl().toString()
            }
            is ApiResponse.Error -> {
                loading = false
                val errorMessage = result.message
                Toast.makeText(context, "Fetch Profile failed: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun load(){

        when(loginRepository.login(LoginRequest(email="name@gmail.com", password = "Qwerty@12"))){
            is ApiResponse.Success -> {
                Toast.makeText(context, "Login successful: ", Toast.LENGTH_SHORT).show()
                fetchProfile()
            }

            is ApiResponse.Error -> {
                Toast.makeText(context, "Login failed:", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun logout() {
        spinning = true
        val logoutRepository = AuthLibrary.createLogoutRepository(authService)

        when (val result = logoutRepository.logout()) {
            is ApiResponse.Success -> {
                spinning = false
                Toast.makeText(context, "Logout Success", Toast.LENGTH_SHORT).show()
            }

            is ApiResponse.Error -> {
                spinning = false
                val errorMessage = result.message
                Toast.makeText(context, "Logout failed: $errorMessage", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }




    LaunchedEffect(Unit) {
        coroutineScope.launch {
            load()
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (loading) {
            Column {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
        } else {
            userData?.let {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Name: ${it.data.name}")
                    Text("Email: ${it.data.email}")
                    Text("Credits: ${it.data.credits}")
                    Text("Cookie: $cookies")
                    Row {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    logout()
                                }
                            },
                        ) {
                            if (spinning) {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .width(20.dp)
                                        .height(20.dp),
                                    color = Color.White

                                )
                            } else {
                                Text(text = "Log Out")
                            }
                        }

                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    fetchProfile()
                                }
                            },
                        ) {

                                Text(text = "Fetch Profile Again")

                        }
                    }
                }
            }
        }
    }
}

