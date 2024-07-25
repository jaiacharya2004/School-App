// StudentLoginScreen.kt
package com.example.schoolapp.authentication.b.studentauth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import com.example.schoolapp.R
import com.example.schoolapp.com.example.schoolapp.authentication.b.studentauth.StudentSignupViewModel

@Composable
fun StudentLoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.DarkGray),


                ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.student_signup), // Replace with your image resource
                        contentDescription = "Signup Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp) // Adjust height as needed
                            .clip(MaterialTheme.shapes.medium)
                    )
                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = "     Student Login ",
                        fontSize = 32.sp,
                        color = Color.Cyan,
                        fontWeight = FontWeight.Medium,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(text = "Email") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .width(100.dp),
                        shape = RoundedCornerShape(20.dp),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        )
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = "Password") },

                        modifier = Modifier
                            .fillMaxWidth()
                            .width(100.dp),
                        shape = RoundedCornerShape(20.dp),



                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val image =
                                if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff

                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(
                                    imageVector = image,
                                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Box (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {

                        Button(onClick = {

                        },
                            colors = ButtonDefaults.buttonColors(Color.Cyan),
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            Text(text = "Login")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    TextButton(onClick = {
                        navController.navigate("student_signup") {
                            popUpTo("student_login") { inclusive = true }
                            launchSingleTop = true
                        }
                    }) {
                        Text(
                            text = "             Don't have an account? Sign Up",
                            color = Color.Cyan
                        )
                    }
                }
            }
        }
    }
}

