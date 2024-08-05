package com.example.schoolapp.authentication.b.common


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.schoolapp.R

@Composable
fun ForgotPasswordScreen(navController: NavController, role: String) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Bigger Text
        Text(
            text = "$role Forget Password",
            fontSize = 24.sp, // Adjust font size as needed
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Smaller Text
        Text(
            text = "Enter your email account to reset password",
            fontSize = 16.sp, // Adjust font size as needed
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Centered Image
        Image(
            painter = painterResource(id = R.drawable.forget_password_2), // Replace with your image resource
            contentDescription = null,
            modifier = Modifier.size(328.dp) // Adjust size as needed
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Email Input
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Enter your email",
                color = Color.Black) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email, // Use an appropriate icon
                    contentDescription = "Email Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Red,
                cursorColor = Color.Black, // Optional: Set cursor color to black
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Reset Password Button
        Button(
            onClick = {
                // Navigate to EnterVerificationCodeScreen
                navController.navigate("enter_verification_code") {
                    // Pass role as an argument if needed
                    launchSingleTop = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text(text = "Reset Password")
        }
        Spacer(modifier = Modifier.height(8.dp))
        // Back to Login Button
        TextButton(onClick = {
            navController.navigate("${role.lowercase()}_login") {
                popUpTo("${role.lowercase()}_forgot_password") { inclusive = true }
                launchSingleTop = true
            }
        }) {
            Text(
                text = "Back to Login",
                color = Color.Black,
            )
        }
    }
}
