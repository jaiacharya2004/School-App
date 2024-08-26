package com.example.schoolapp.studybase.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.Visibility
import androidx.navigation.NavController
import com.example.schoolapp.R

@Composable
fun LoginScreen(navController: NavController) {
    val navController = navController
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(true) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7F3FF)), // Light blue background
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Studybase",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4285F4), // Blue text color
            modifier = Modifier.padding(top = 86.dp, bottom = 32.dp)
        )

        Spacer(modifier = Modifier.height(90.dp))

        OutlinedTextField(
            value = emailState.value,
            onValueChange = { emailState.value = it },
            label = { Text(text = "Email") },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 17.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Password field with visibility toggle
        OutlinedTextField(
            value = passwordState.value,
            onValueChange = { passwordState.value = it },
            label = { Text(text = "Password") },
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
                    passwordVisibility = !passwordVisibility
                })
                {
                    val icon: Painter = if (passwordVisibility) {
                        painterResource(id = R.drawable.visibilityopen)
                    } else {
                        painterResource(id = R.drawable.visibility_off)
                    }
                    Icon(painter = icon, contentDescription = "Toggle visibility")
                }
            },
            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation()
        )




        Spacer(modifier = Modifier.height(36.dp))

        // Next Button
        Button(
            onClick = { /* Next clicked */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = {
            navController.navigate("forget_screen")

        }) {
            Text(text = "Forgot Password?")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = {
            navController.navigate("activate_screen")
        }) {
            Text(
                text = "Activate Your Account",

                )
        }
    }



    }

