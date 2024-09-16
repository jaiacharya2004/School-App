package com.example.schoolapp.authentication.b.Activate


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
import androidx.compose.ui.graphics.Brush
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
import androidx.navigation.NavController
import com.example.schoolapp.R
import com.example.schoolapp.authentication.b.authErrors.ErrorViewModel
import com.example.schoolapp.authentication.b.model.Response.Loading
import com.example.schoolapp.authentication.b.model.Response.Failure
import com.example.schoolapp.authentication.b.model.Response.Success



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivatePassword(navController: NavController,viewModel: ActivateViewModel ) {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var newPasswordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var newPasswordError by remember { mutableStateOf(false) }
    var confirmPasswordError by remember { mutableStateOf(false) }
    val errorViewModel = ErrorViewModel()
    val errorState by errorViewModel.errorState.collectAsState()
    val activateViewModel = viewModel
    val userCreationState = activateViewModel.userCreationStatus
    var isLoading by remember { mutableStateOf(false) }
    var buttonClickCount = remember { mutableIntStateOf(0) }





    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6A1B9A), // Purple color
                        Color.White
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("activate_screen")
                }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Activate Account",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Study Base",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4285F4), // Blue text color
                modifier = Modifier.padding(top = 32.dp, bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(42.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                elevation = CardDefaults.cardElevation(4.dp),
                colors = CardDefaults.cardColors(Color.White),
                shape = RoundedCornerShape(24.dp),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(32.dp))

                    // New Password field with visibility toggle
                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = {
                            newPassword = it
                            newPasswordError = false
                        },
                        label = { Text(text = "New Password") },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                        ),
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
                        else PasswordVisualTransformation(),
                        isError = newPasswordError
                    )

                    // Display error message for new password
                    if (newPasswordError) {
                        Text(
                            text = "Please create your password",
                            color = Color.Red,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Confirm Password field with visibility toggle
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            confirmPasswordError = false
                        },
                        label = { Text(text = "Confirm Password") },
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                        ),
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
                        else PasswordVisualTransformation(),
                        isError = confirmPasswordError
                    )

                    if (confirmPasswordError) {
                        Text(
                            text = "Please confirm your password",
                            color = Color.Red,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                       // password matching operation pending
                        onClick = {

                            newPasswordError = newPassword.isEmpty()
                            confirmPasswordError = confirmPassword.isEmpty()

                            if (!newPasswordError && !confirmPasswordError && newPassword == confirmPassword) {
                                buttonClickCount.intValue++
                                isLoading = true

                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp)
                    ) {
                        Text(text = "Confirm")
                    }

                    if (buttonClickCount.intValue > 0) {
                        LaunchedEffect(key1 = buttonClickCount) {
                            if (newPassword.isNotEmpty()) {
                                userCreationState.value = activateViewModel.confirmPassword(password = newPassword)

                                when(userCreationState.value){
                                    is Success -> {

                                        activateViewModel.activateUser()
                                        // complete the instantiation of the user
                                        navController.navigate("home_screen")

                                    }
                                    is Failure -> {

                                    }
                                     is Loading -> {

                                     }
                                }
                            }
                        }


                    }
                }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }

