package com.example.schoolapp.authentication.b.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ResetPasswordScreen(navController: NavController, role: String) {
    var newPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$role Reset Your Password")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text(text = "New Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Handle password reset logic
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Set New Password")
        }
    }
}
