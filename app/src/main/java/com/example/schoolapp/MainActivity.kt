package com.example.schoolapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.schoolapp.HomeScreen.PrincipalHomeScreen
import com.example.schoolapp.HomeScreen.StudentHomeScreen
import com.example.schoolapp.HomeScreen.TeacherHomeScreen
import com.example.schoolapp.com.example.schoolapp.authentication.loginScreen.schoolHead.PrincipalLoginScreen
import com.example.schoolapp.com.example.schoolapp.authentication.loginScreen.studentauth.StudentLoginScreen
import com.example.schoolapp.com.example.schoolapp.authentication.loginScreen.teacherauth.TeacherLoginScreen
import com.example.schoolapp.com.example.schoolapp.authentication.loginScreen.schoolHead.PrincipalSignupScreen
import com.example.schoolapp.com.example.schoolapp.authentication.loginScreen.studentauth.StudentSignupScreen
import com.example.schoolapp.com.example.schoolapp.authentication.loginScreen.teacherauth.TeacherSignupScreen
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
    val authViewModel: AuthViewModel = viewModel()

    NavHost(navController = navController, startDestination = "role_selection_screen") {
        composable("role_selection_screen") { RoleSelectionScreen(navController) }
        composable("principal_home") { PrincipalHomeScreen(navController, authViewModel) }
        composable("principal_login") { PrincipalLoginScreen(navController, authViewModel) }
        composable("principal_signup") { PrincipalSignupScreen(navController, authViewModel) }
        composable("teacher_home") { TeacherHomeScreen(navController, authViewModel) }
        composable("teacher_login") { TeacherLoginScreen(navController, authViewModel) }
        composable("teacher_signup") { TeacherSignupScreen(navController, authViewModel) }
        composable("student_home") { StudentHomeScreen(navController, authViewModel) }
        composable("student_login") { StudentLoginScreen(navController, authViewModel) }
        composable("student_signup") { StudentSignupScreen(navController, authViewModel) }
    }
}
