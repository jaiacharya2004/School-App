package com.example.schoolapp

import PrincipalHomeScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schoolapp.loginScreen.PrincipalLoginScreen
import com.example.schoolapp.loginScreen.StudentLoginScreen
import com.example.schoolapp.loginScreen.TeacherLoginScreen
import com.example.schoolapp.signupScreen.PrincipalSignupScreen
import com.example.schoolapp.signupScreen.StudentSignupScreen
import com.example.schoolapp.signupScreen.TeacherSignupScreen
import com.example.schoolapp.ui.theme.SchoolAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SchoolAppTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "principal_home") {
        composable("teacher_login") { TeacherLoginScreen(navController) }
        composable("teacher_signup") { TeacherSignupScreen(navController) }
        composable("student_login") { StudentLoginScreen(navController) }
        composable("student_signup") { StudentSignupScreen(navController) }
        composable("principal_login") { PrincipalLoginScreen(navController) }
        composable("principal_signup") { PrincipalSignupScreen(navController) }
        composable("principal_home") { PrincipalHomeScreen(navController) }
    }
}
