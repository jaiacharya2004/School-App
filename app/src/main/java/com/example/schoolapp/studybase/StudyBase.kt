package com.example.schoolapp.studybase

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun StudyBaseScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFE3F2FD) // Background color (light blue)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(start = 26.dp, end = 26.dp),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 120.dp),
                text = "Studybase",
                style = TextStyle(
                    color = Color(0xFF1976D2), // Text color (blue)
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(120.dp))


            Button(
                onClick = { /* Handle Activate */ },
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 38.dp)
            ) {
                Text(text = "Activate", fontSize = 20.sp)
            }

            Button(
                onClick = { /* Handle Login */ },
                shape = RectangleShape,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = "Login", fontSize = 20.sp)
            }
        }
    }
}
