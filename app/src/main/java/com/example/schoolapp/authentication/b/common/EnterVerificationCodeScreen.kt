package com.example.schoolapp.authentication.b.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.schoolapp.R // Ensure you have the correct import for your drawable resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterVerificationCodeScreen(navController: NavController, role: String) {
    var verificationCode by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val codeLength = 6
    val codeDigits = remember { Array(codeLength) { mutableStateOf("") } }
    val focusRequesters = remember { Array(codeLength) { FocusRequester() } }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Enter Verification Code",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Add Image
        Image(
            painter = painterResource(id = R.drawable.enter_verification_code_2), // Replace with your image resource ID
            contentDescription = "Verification Code Image",
            modifier = Modifier
                .size(320.dp) // Adjust size as needed
                .padding(horizontal = 16.dp) // Adjust padding as needed
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (i in 0 until codeLength) {
                OutlinedTextField(
                    value = codeDigits[i].value,
                    onValueChange = {
                        if (it.length <= 1 && it.all { char -> char.isDigit() }) {
                            codeDigits[i].value = it
                            if (it.isNotEmpty()) {
                                if (i < codeLength - 1) {
                                    focusRequesters[i + 1].requestFocus()
                                } else {
                                    // Hide the keyboard if all inputs are filled
                                    keyboardController?.hide()
                                }
                            } else if (it.isEmpty() && i > 0) {
                                focusRequesters[i - 1].requestFocus()
                            }
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .aspectRatio(1f)
                        .focusRequester(focusRequesters[i]),
                    textStyle = LocalTextStyle.current.copy(fontSize = 20.sp, textAlign = androidx.compose.ui.text.style.TextAlign.Center, color = Color.Black),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = if (i < codeLength - 1) ImeAction.Next else ImeAction.Done
                    ),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(96.dp))
        Button(
            onClick = {
                val enteredCode = codeDigits.joinToString("") { it.value }
                if (enteredCode.length == codeLength) {
                    navController.navigate("reset_password") {
                        launchSingleTop = true
                    }
                    keyboardController?.hide()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            )
        ) {
            Text(text = "Verify Code")
        }
    }

    // Request focus for the first field when the composable is initially loaded
    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }
}
