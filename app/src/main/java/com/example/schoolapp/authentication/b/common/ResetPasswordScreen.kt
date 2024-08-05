package com.example.schoolapp.authentication.b.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ResetPasswordScreen(navController: NavController, role: String) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordMismatch by remember { mutableStateOf(false) }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 116.dp, start = 26.dp, end = 26.dp),
    ) {
        Text(text = "    Reset Your Password", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(46.dp))

        // New Password Field with Icon and Visibility Toggle
        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text(text = "New Password") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock Icon")
            },
            trailingIcon = {
                IconButton(onClick = { newPasswordVisible = !newPasswordVisible }) {
                    Icon(
                        imageVector = if (newPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (newPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Red,
                cursorColor = Color.Black, // Optional: Set cursor color to black
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
            ),
            visualTransformation = if (newPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password Field with Icon and Visibility Toggle
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = "Confirm Password") },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock Icon")
            },
            trailingIcon = {
                IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                    Icon(
                        imageVector = if (confirmPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (confirmPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Red,
                cursorColor = Color.Black, // Optional: Set cursor color to black
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
            ),
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
        )

        // Show error message if passwords do not match
        if (passwordMismatch) {
            Text(
                text = "Passwords do not match!",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(156.dp))

        Button(
            onClick = {
                // Handle password reset logic
                if (newPassword == confirmPassword) {
                    // Reset passwordMismatch since passwords match
                    passwordMismatch = false

                    // Navigate to the appropriate home screen based on the role
                    when (role) {
                        "Principal" -> navController.navigate("principal_home") {
                            launchSingleTop = true
                        }
                        "Teacher" -> navController.navigate("teacher_home") {
                            launchSingleTop = true
                        }
                        "Student" -> navController.navigate("student_home") {
                            launchSingleTop = true
                        }
                    }
                } else {
                    // Show error message
                    passwordMismatch = true
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Set New Password", color = Color.White)
        }
    }
}
