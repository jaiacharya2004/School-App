package com.example.schoolapp.authentication.b


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.navigation.NavController

//This is to display user details

@Composable
fun NewScreen(navController: NavController){

    val viewModel = remember{ActivateViewModel(navController)}



    val user = viewModel.user.collectAsState()

    Text(
        text = user.value.name,

        )

}