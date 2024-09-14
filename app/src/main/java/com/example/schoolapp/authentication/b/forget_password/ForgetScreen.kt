package com.example.schoolapp.authentication.b.forget_password

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.schoolapp.authentication.b.model.Response.Success
import com.example.schoolapp.authentication.b.model.Response.Failure
import com.example.schoolapp.authentication.b.model.Response.Loading


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetScreen(navController: NavController) {
    val emailState = remember { mutableStateOf("") }
    val errorMessageState = remember { mutableStateOf("") }
    var buttonClickCount = remember { mutableIntStateOf(0) }
    var forgetPasswordViewModel = ForgetPassViewModel()

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
            // Top App Bar with back button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.navigate("login_screen")
                }) {
                    Icon(
                        Icons.Filled.ArrowBack, contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Forget Password",
                    fontSize = 20.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Studybase",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4285F4), // Blue text color
                modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
            )

            Spacer(modifier = Modifier.height(170.dp))

            OutlinedTextField(
                value = emailState.value,
                onValueChange = {
                    emailState.value = it
                    if (it.isNotEmpty()) {
                        errorMessageState.value = ""
                    }
                },
                label = { Text(text = "Email") },
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Black,    // Border color when focused
                    unfocusedBorderColor = Color.Black,  // Border color when not focused
                    cursorColor = Color.Black,           // Cursor color
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 17.dp),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                textStyle = TextStyle(color = Color.Black, fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (errorMessageState.value.isNotEmpty()) {
                Text(
                    text = errorMessageState.value,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Enter your email address to reset your password",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (emailState.value.isEmpty()) {
                        errorMessageState.value = "Email field cannot be empty"
                    } else {
                        buttonClickCount.intValue++

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Text(text = "Next")
            }

            if (buttonClickCount.intValue > 0) {
                LaunchedEffect(key1 = buttonClickCount) {
                    val varName = forgetPasswordViewModel.sendPasswordResetEmail(emailState.value)
                    if(varName == Success(true)){
              // open the gmail app
                    }

                }
            }
        }
    }
}

