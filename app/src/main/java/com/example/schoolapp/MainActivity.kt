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
import com.example.schoolapp.authentication.b.schoolHead.PrincipalHomeScreen
import com.example.schoolapp.authentication.b.studentauth.StudentHomeScreen
import com.example.schoolapp.authentication.b.teacherauth.TeacherHomeScreen

import com.example.schoolapp.authentication.b.schoolHead.PrincipalLoginScreen
import com.example.schoolapp.authentication.b.studentauth.StudentLoginScreen
import com.example.schoolapp.authentication.b.schoolHead.PrincipalSignupScreen
import com.example.schoolapp.authentication.b.schoolHead.principalforgetpassword.ResetPasswordScreen
import com.example.schoolapp.authentication.b.studentauth.StudentSignupScreen
import com.example.schoolapp.authentication.b.teacherauth.TeacherLoginScreen
import com.example.schoolapp.authentication.b.teacherauth.TeacherSignupScreen

import com.example.schoolapp.ui.theme.SchoolAppTheme
import com.google.firebase.auth.FirebaseAuth

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
    NavHost(navController = navController, startDestination = "role_selection_screen") {
        composable("role_selection_screen") { RoleSelectionScreen(navController) }
        composable("principal_home") { PrincipalHomeScreen(navController) }
        composable("principal_login") { PrincipalLoginScreen(navController) }
        composable("principal_forget_password") { ResetPasswordScreen(navController) }
        composable("principal_signup") { PrincipalSignupScreen(navController) }
        composable("teacher_home") { TeacherHomeScreen(navController) }
        composable("teacher_login") { TeacherLoginScreen(navController) }
        composable("teacher_signup") { TeacherSignupScreen(navController) }
        composable("student_home") { StudentHomeScreen(navController) }
        composable("student_login") { StudentLoginScreen(navController) }
        composable("student_signup") { StudentSignupScreen(navController) }
    }
}
