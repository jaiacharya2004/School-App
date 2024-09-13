package com.example.schoolapp.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.schoolapp.authentication.b.Activate.ActivatePassword
import com.example.schoolapp.authentication.b.Activate.ActivateScreen
import com.example.schoolapp.authentication.b.Activate.ActivateViewModel
import com.example.schoolapp.authentication.b.forget_password.ForgetScreen
import com.example.schoolapp.authentication.b.login.LoginScreen
import com.example.schoolapp.errorMessages.authenticationErrorMessages.ErrorViewModel
import com.example.schoolapp.presentation.HomeScreen


sealed class Route(val name: String) {
    data object ActivateScreen : Route(name = "activate_screen")
    data object NewScreen : Route(name = "display_screen")
    data object ForgetScreen : Route(name = "forget_screen")
    data object LoginScreen : Route(name = "login_screen")
    data object ActivatePassword : Route(name = "activate_password")

    data object HomeScreen : Route(name = "home_screen")
}

@Composable
fun SchoolAppNavHost(navController: NavHostController,viewModel:ActivateViewModel) {
    val errorViewModel: ErrorViewModel = viewModel()
    val activateViewModel = viewModel
    var authViewModel = ActivateViewModel()
    // Check if the user is logged in or not by saving this data into a datastore and accessing it after the app is reopened by the user.
    // The user type data in the User class should listen to data in Role selection screens, which will help decide which kind of user has logged in.
    // We can instantiate the User class only if either the auth (Signup or Login) is successful.
    // And when the user logs out by pressing the logout button, we have to de-allocate the memory.

    NavHost(
        navController = navController,
        startDestination = Route.ActivateScreen.name
    ) {
        composable(route = Route.ActivateScreen.name) {
            ActivateScreen(navController,activateViewModel)
        }
//        composable(route = Route.NewScreen.name) {
//            Route.NewScreen(navController)
//        }
        composable(route = Route.ForgetScreen.name) {
            ForgetScreen(navController)
        }
        composable(route = Route.LoginScreen.name) {
            LoginScreen(navController)
        }
        composable(route = Route.ActivatePassword.name) { // Corrected route name
            ActivatePassword(navController,activateViewModel )
        }
        composable(route = Route.HomeScreen.name) {
            HomeScreen(navController)
        }


    }
}


