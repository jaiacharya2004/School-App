package com.example.schoolapp.studybase.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.example.schoolapp.authentication.b.ActivateViewModel
import com.example.schoolapp.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgetScreen(navController: NavController) {
    val navController = navController
    val emailState = remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE7F3FF)), // Light blue background
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Top App Bar with back button
        TopAppBar(
            title = { Text(text = "Forgot Password") },
            navigationIcon = {
                IconButton(onClick = {
                    navController.navigate("login_screen")}) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.White)
        )

        Text(
            text = "Studybase",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF4285F4), // Blue text color
            modifier = Modifier.padding(top = 16.dp, bottom = 32.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

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
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(color = Color.Black, fontSize = 18.sp)
        )

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
            onClick = { /* Next clicked */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Text(text = "Next")
        }
    }
}

