package com.example.schoolapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RoleSelectionScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .size(230.dp)
                    .clickable {
                        navController.navigate("principal_login")
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Principal Login",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.principal),
                        contentDescription = "Principal Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .size(230.dp)
                    .clickable {
                        navController.navigate("teacher_login")
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Teacher Login",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.teacher),
                        contentDescription = "Teacher Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .size(230.dp)
                    .clickable {
                        navController.navigate("student_login")
                    }
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Student Login",
                        modifier = Modifier.padding(16.dp),
                        fontWeight = FontWeight.Bold,
                    )
                    Image(
                        painter = painterResource(id = R.drawable.student),
                        contentDescription = "Student Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
