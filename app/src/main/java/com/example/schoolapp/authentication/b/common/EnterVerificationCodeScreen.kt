package com.example.schoolapp.authentication.b.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun EnterVerificationCodeScreen(navController: NavController, role: String) {
    var verificationCode by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "$role Enter Verification Code")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = verificationCode,
            onValueChange = { verificationCode = it },
            label = { Text(text = "Verification Code") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Handle verification code validation and navigate to the next screen
                navController.navigate("reset_password") {
                    // Pass role as an argument if needed
                    launchSingleTop = true
                }
                // Hide the keyboard after the button is clicked
                keyboardController?.hide()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Verify Code")
        }
    }
}
