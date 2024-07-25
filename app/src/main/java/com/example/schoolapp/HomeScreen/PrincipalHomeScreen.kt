package com.example.schoolapp.HomeScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.schoolapp.AuthState
import com.example.schoolapp.AuthViewModel

@Composable
fun PrincipalHomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel = viewModel()
) {
    val authState by authViewModel.authState.observeAsState()

    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Unauthenticated -> {
                navController.navigate("principal_login") {
                    popUpTo("principal_home") { inclusive = true }
                }
            }
            is AuthState.Error -> {
                // Show the error message (e.g., Snackbar or Toast)
                val errorMessage = (authState as AuthState.Error).message
                // Handle error display here
            }
            else -> {}
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Home Page", fontSize = 32.sp, color = Color.Black)
        TextButton(onClick = {
            authViewModel.signout()
        }) {
            Text(text = "Sign out")
        }
    }
}
