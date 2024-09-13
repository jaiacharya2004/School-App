package com.example.schoolapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.schoolapp.authentication.b.Activate.ActivateViewModel
import com.example.schoolapp.ui.theme.SchoolAppTheme
import com.example.schoolapp.navigation.SchoolAppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Wrap the entire app content with the custom theme

            SchoolAppTheme {
                // Set up the navigation controller
                val navController = rememberNavController()
                val activateViewModel = ActivateViewModel()

                // Start your navigation host here
                SchoolAppNavHost(navController = navController,activateViewModel)
            }
        }
    }
}