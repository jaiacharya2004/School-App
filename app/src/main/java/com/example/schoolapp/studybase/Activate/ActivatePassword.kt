package com.example.schoolapp.studybase.Activate


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.schoolapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivatePassword(navController: NavController) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7F3FF)), // Light blue background
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        TopAppBar(
            title = { Text(text = "Activate Account") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("activate_screen")}) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
        )


        Text(
            text = "Study Base",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4285F4), // Blue text color
            modifier = Modifier.padding(top = 86.dp, bottom = 32.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // New Password field with visibility toggle
        OutlinedTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            label = { Text(text = "New Password") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            trailingIcon = {
                IconButton(onClick = {
                    newPasswordVisible = !newPasswordVisible
                }) {
                    val icon: Painter = if (newPasswordVisible) {
                        painterResource(id = R.drawable.visibilityopen)
                    } else {
                        painterResource(id = R.drawable.visibility_off)
                    }
                    Icon(painter = icon, contentDescription = "Toggle visibility")
                }
            },
            visualTransformation = if (newPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password field with visibility toggle
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text(text = "Confirm Password") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
            trailingIcon = {
                IconButton(onClick = {
                    confirmPasswordVisible = !confirmPasswordVisible
                }) {
                    val icon: Painter = if (confirmPasswordVisible) {
                        painterResource(id = R.drawable.visibilityopen)
                    } else {
                        painterResource(id = R.drawable.visibility_off)
                    }
                    Icon(painter = icon, contentDescription = "Toggle visibility")
                }
            },
            visualTransformation = if (confirmPasswordVisible) VisualTransformation.None
            else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(36.dp))

        Text(
            text = "Create a new password for your account.",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))


        // Confirm Button
        Button(
            onClick = { /* Confirm button clicked */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            Text(text = "Confirm")
        }

    }
}
