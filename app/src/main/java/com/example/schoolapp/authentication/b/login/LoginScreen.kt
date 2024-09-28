package com.example.schoolapp.authentication.b.login

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.ui.graphics.Path
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

import androidx.navigation.NavController
import com.example.schoolapp.R
import com.example.schoolapp.authentication.b.Activate.ActivateViewModel
import com.example.schoolapp.authentication.b.model.Response.Success
import com.example.schoolapp.authentication.b.model.Response.Failure
import com.example.schoolapp.authentication.b.model.Response.Loading
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController,authViewModel: ActivateViewModel) {

    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordVisibility by remember { mutableStateOf(true) }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var buttonClickCount = remember { mutableIntStateOf(0) }
    var userLoginStatus = authViewModel.userSignInResponse


    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Draw the curved background
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            val curveHeight = 400f // Height of the curve
            val curveControlPoint1 = Offset(width * 0.5f, height * 0.3f)
            val curveControlPoint2 = Offset(width * 0.3f, height * 0.6f)

            val path = Path().apply {
                moveTo(0f, 0f)
                lineTo(0f, height * 0.4f)
                cubicTo(
                    curveControlPoint1.x, curveControlPoint1.y,
                    curveControlPoint2.x, curveControlPoint2.y,
                    width, height * 0.4f
                )
                lineTo(width, 0f)
                close()
            }

            drawPath(
                path = path,
                color = Color(0xFF9C27B0) // Purple color
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(120.dp))

            Text(
                text = "Studybase",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4285F4),
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Spacer(modifier = Modifier.height(200.dp))

            OutlinedTextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                    if (emailError) emailError = false // Reset error when field is filled
                },
                label = { Text(text = "Email") },
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
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp)
            )

            if (emailError) {
                Text(
                    text = "Please enter your email",
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 34.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = passwordState.value,
                onValueChange = {
                    passwordState.value = it
                    if (passwordError) passwordError = false // Reset error when field is filled
                },
                label = { Text(text = "Password") },
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
                        passwordVisibility = !passwordVisibility
                    }) {
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

            if (passwordError) {
                Text(
                    text = "Please enter your password",
                    color = Color.Red,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(start = 34.dp)
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = {
                    val isEmailEmpty = emailState.value.isEmpty()
                    val isPasswordEmpty = passwordState.value.isEmpty()

                    emailError = isEmailEmpty
                    passwordError = isPasswordEmpty

                    if (!isEmailEmpty && !isPasswordEmpty) {
                        buttonClickCount.intValue++
                        isLoading = true

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
            ) {
                Text(text = "Login", fontSize = 22.sp)
            }


            if(buttonClickCount.intValue>0) {
                LaunchedEffect(key1 = buttonClickCount) {

                    userLoginStatus.value = authViewModel.logInWithEmailAndPassword(emailState.value,passwordState.value)
                    Log.d("userLoginStatus final", "LoginScreen: $userLoginStatus")


                    when(userLoginStatus.value) {
                        is Success -> {
                            isLoading = false
                            navController.navigate("home_screen")
                        }
                        is Failure ->{
                            Log.d("userLoginStatus Failure", "LoginScreen: $userLoginStatus")

                        }
                        is Loading ->{
                            isLoading = true
                            Log.d("userLoginStatus Loading", "LoginScreen: $userLoginStatus")

                        }
                    }

            }
                }

                Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                navController.navigate("forget_screen")
            }) {
                Text(text = "Forgot Password?")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {

            }
            ) {
                    Text(
                        text = "Activate Your Account",
                    )
            }
        }
    }
}

