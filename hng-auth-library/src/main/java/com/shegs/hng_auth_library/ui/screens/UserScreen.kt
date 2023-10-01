package com.shegs.hng_auth_library.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.shegs.hng_auth_library.authlibrary.AuthLibrary
import com.shegs.hng_auth_library.model.AuthResponse
import com.shegs.hng_auth_library.model.LoginRequest
import com.shegs.hng_auth_library.network.ApiResponse
import kotlinx.coroutines.launch

@Composable
fun UserDataScreen() {
    var userData by remember { mutableStateOf<AuthResponse?>(null) }
    var loading by remember { mutableStateOf(true) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()


    val authService = AuthLibrary.createAuthService()
    val dataStoreRepository = AuthLibrary.createDataStoreRepository(context)
    val loginRepository = AuthLibrary.createLoginRepository(authService, dataStoreRepository)

    suspend fun load(){
        val profileRepository = AuthLibrary.createProfileRepository(authService)

        when(loginRepository.login(LoginRequest(email="test@mail.com", password = "password"))){
            is ApiResponse.Success -> {
                Toast.makeText(context, "Login successful: ", Toast.LENGTH_SHORT).show()
                when (val result = profileRepository.profile()) {
                    is ApiResponse.Success -> {
                        userData = result.data
                        loading = false
                        Toast.makeText(context, "Fetch Profile success", Toast.LENGTH_SHORT).show()
                    }
                    is ApiResponse.Error -> {
                        loading = false
                        val errorMessage = result.message
                        Toast.makeText(context, "Fetch Profile failed: $errorMessage", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            is ApiResponse.Error -> {
                Toast.makeText(context, "Login failed:", Toast.LENGTH_SHORT).show()
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
            // Display a loading indicator while loading data
            Column {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
        } else {
            // Display user data when loading is complete
            userData?.let {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Name: ${it.data.name}")
                    Text("Email: ${it.data.email}")
                    Text("Credits: ${it.data.credit}")
                }
            }
        }
    }
}

