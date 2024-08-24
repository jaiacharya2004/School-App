package com.example.schoolapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schoolapp.authentication.b.ActivateScreen
import com.example.schoolapp.authentication.b.common.EnterVerificationCodeScreen
import com.example.schoolapp.authentication.b.common.ForgotPasswordScreen
import com.example.schoolapp.authentication.b.common.ResetPasswordScreen
import com.example.schoolapp.navigation.SchoolAppNavHost

import com.example.schoolapp.ui.theme.SchoolAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SchoolAppTheme {
                val navController = rememberNavController()
                   SchoolAppNavHost(navController)
                    ActivateScreen(navController)
            //                AppNavigation(navController)

            }
        }
    }
}


